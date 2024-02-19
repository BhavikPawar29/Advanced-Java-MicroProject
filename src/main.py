import torch
import torch.nn as nn
import torch.nn.functional as F
import torch_geometric as pyg_nn
import networkx as nx
from Bio.SeqRecord import SeqRecord
from torch_geometric.utils import to_networkx
from Bio.Seq import Seq
from BCBio import GFF

import logging
import re
from Bio import SeqIO
import pandas as pd
import numpy as np
import tensorflow as tf
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import LabelEncoder
from torch_geometric.nn import GCNConv
import matplotlib.pyplot as plt
import pickle

# Parameters
epochs, batch_size = 100, 32


# -------------- DATA PREPROCESSING  ------------------#

def _extract_seq_id(description):
    pattern = r"(\w+\.\d+)"
    match = re.search(pattern, description)
    return match.group(1) if match else None


def read_gff(gff_file, chunk_size=1000):
    logging.info("Reading GFF data in chunks...")
    gff_data_list = []

    with open(gff_file, "r") as file:
        for record in GFF.parse(file, 'gff'):
            for feature in record.features:
                seq_id = record.id
                source = feature.qualifiers.get('source', [''])[0]
                feature_type = feature.type
                start = feature.location.start
                end = feature.location.end
                strand = feature.location.strand

                strand = 1 if strand == 1 else -1

                attributes = feature.qualifiers.get('attributes', '')

                gff_data_list.append({
                    "seq_id": seq_id,
                    "source": source,
                    "feature_type": feature_type,
                    "start": start,
                    "end": end,
                    "strand": strand,
                    "attributes": attributes,
                    "feature": feature
                })

                if len(gff_data_list) >= chunk_size:
                    yield pd.DataFrame(gff_data_list)
                    gff_data_list = []

    if gff_data_list:
        yield pd.DataFrame(gff_data_list)


def read_fasta(fasta_file, chunk_size=1000):
    logging.info("Reading FASTA data...")
    sequences = []
    gene_data = []

    with open(fasta_file, "r") as handle:
        records = list(SeqIO.parse(handle, "fasta"))

        sequences = [str(record.seq) for record in records]

        gene_data = [
            {
                'seq_id': _extract_seq_id(record.description),
                **{
                    key.strip(): value.strip()
                    for item in record.description.split("[")[:]
                    for parts in [item.strip("]").split("=", 1)]
                    if len(parts) == 2
                    for key, value in [parts]
                }
            }
            for record in records
        ]

    df_sequences = pd.DataFrame({'Sequence': sequences})
    df_gene_data = pd.DataFrame(gene_data)

    return df_sequences, df_gene_data


# ------------- CNN MODEL FUNCTIONS ----------------- #

def build_model(input_shape, num_classes, learning_rate=0.001):
    model = tf.keras.Sequential([
        tf.keras.layers.Conv2D(32, 5, activation='relu', input_shape=input_shape),
        tf.keras.layers.MaxPooling2D(2),
        tf.keras.layers.Conv2D(64, 3, activation='relu'),
        tf.keras.layers.MaxPooling2D(2),
        tf.keras.layers.Conv2D(128, 3, activation='relu'),
        tf.keras.layers.MaxPooling2D(2),
        tf.keras.layers.Flatten(),
        tf.keras.layers.Dense(256, activation='relu'),
        tf.keras.layers.Dropout(0.5),
        tf.keras.layers.Dense(num_classes, activation='softmax')
    ])

    model.compile(
        optimizer=tf.keras.optimizers.Adam(learning_rate=learning_rate),
        loss='categorical_crossentropy',
        metrics=['accuracy']
    )
    return model


def _encode_labels(y_train_annotation):
    label_encoder = LabelEncoder()
    y_train_annotation_encoded = label_encoder.fit_transform(y_train_annotation)
    num_classes_annotation = len(label_encoder.classes_)
    return y_train_annotation_encoded, num_classes_annotation


def train_model(x_train, y_train_annotation, epochs, batch_size, task=None):
    X_train, y_train = _extract(x_train, y_train_annotation, task)

    X_train_encoded, _ = _one_hot_encoding(X_train)
    input_shape = (X_train_encoded.shape[1], 4)

    X_train_prediction, X_temp_prediction, y_train_prediction, y_temp_prediction = train_test_split(
        X_train_encoded,
        y_train,
        test_size=0.2,
        random_state=42
    )

    X_val_prediction, X_test_prediction, y_val_prediction, y_test_prediction = train_test_split(
        X_temp_prediction,
        y_temp_prediction,
        test_size=0.4,
        random_state=42
    )

    y_train_annotation_encoded, num_classes_annotation = _encode_labels(y_train_prediction)

    model = build_model(input_shape, num_classes_annotation, learning_rate=0.001)

    history = model.fit(
        X_train_prediction,
        y_train_annotation_encoded,
        validation_data=(X_val_prediction, y_val_prediction),
        epochs=epochs,
        batch_size=batch_size,
        verbose=1
    )

    test_acc, test_loss = model.evaluate(X_test_prediction, y_test_prediction)

    logging.info(f"Test Accuracy: {test_acc},  Test Loss: {test_loss}")

    return model, history, test_acc, test_loss


# ------------- GENOME ASSEMBLY ------------- #

class DeBruijnGNN(nn.Module):
    def __init__(self, input_dim, hidden_dim, output_dim):
        super(DeBruijnGNN, self).__init__()
        self.conv1 = GCNConv(input_dim, hidden_dim)
        self.conv2 = GCNConv(hidden_dim, output_dim)

    def forward(self, data):
        x, edge_index = data.x, data.edge_index
        x = self.conv1(x, edge_index)
        x = F.relu(x)
        x = self.conv2(x, edge_index)
        return F.log_softmax(x, dim=1)

    @staticmethod
    def de_novo_assembly(df_sequences, chunk_size=100):
        kmer_size = 25
        g = nx.Graph()

        sequences = df_sequences['Sequence']

        for chunk_start in range(0, len(sequences), chunk_size):
            chunk_end = min(chunk_start + chunk_size, len(sequences))
            chunk_sequences = sequences.iloc[chunk_start:chunk_end]

            seq = chunk_sequences.iloc[0]

            kmer_to_vertex = {}

            # Create k-mers and add nodes to the graph
            kmer_to_vertex.update(
                (kmer, len(kmer_to_vertex)) for kmer in
                set(seq[i:i + kmer_size] for i in range(len(seq) - kmer_size + 1))
            )
            g.add_nodes_from(range(len(kmer_to_vertex)))

            # Create edges between k-mers and add them to the graph
            edges = set((seq[i:i + kmer_size], seq[i + 1:i + kmer_size + 1]) for i in range(len(seq) - kmer_size))
            g.add_edges_from(
                (kmer_to_vertex[kmer1], kmer_to_vertex[kmer2]) for kmer1, kmer2 in edges
            )

        data = to_networkx(g)
        edge_index = torch.tensor(list(data.edges)).t().contiguous()
        x = torch.eye(len(kmer_to_vertex))

        return data, edge_index, x, kmer_to_vertex

    @staticmethod
    def train_gnn(data, edge_index, x):
        input_dim = data.num_nodes
        hidden_dim = 64
        output_dim = data.num_nodes
        model = DeBruijnGNN(input_dim, hidden_dim, output_dim)
        optimizer = torch.optim.Adam(model.parameters(), lr=0.01)
        criterion = nn.CrossEntropyLoss()

        for epoch in range(100):
            optimizer.zero_grad()
            out = model(data)
            loss = criterion(out, data.y)
            loss.backward()
            optimizer.step()

        return model

    @staticmethod
    def predict(sequences, model, data, kmer_to_vertex):
        with torch.no_grad():
            out = model(data)

        _, best_path_indices = torch.max(out, dim=1)
        best_path = [list(kmer_to_vertex.keys())[i] for i in best_path_indices]

        assembled_genome = Seq("".join(best_path))

        num_reads = len(sequences)
        num_contigs = len(best_path)

        return SeqRecord(
            assembled_genome, id="assembled_genome",
            description=f"Assembled Genome\nReads: {num_reads}\nContigs: {num_contigs}",
            annotations={"num_reads": num_reads,
                         "num_contigs": num_contigs}
        )
def predict_and_annotate(model, X):
    predictions_proba = model.predict(X)
    predicted_classes = np.argmax(predictions_proba, axis=1)
    return predicted_classes


# ----------------- GENE PREDICTIONS  ------------------#

def _one_hot_encoding(sequences, max_sequence_length=None):
    base_mapping = {'A': [1, 0, 0, 0], 'T': [0, 1, 0, 0], 'G': [0, 0, 1, 0], 'C': [0, 0, 0, 1]}
    default_mapping = [0.25, 0.25, 0.25, 0.25]

    encoded_sequences = []
    max_len = 0

    for sequence_entry in sequences:
        header, sequence = sequence_entry.split('\n', 1)
        sequence = sequence.replace('\n', '').upper()

        max_len = max(max_len, len(sequence))

        one_hot_sequence = [base_mapping.get(nt, default_mapping) for nt in sequence]
        encoded_sequences.append(one_hot_sequence)

    if max_sequence_length:
        for i in range(len(encoded_sequences)):
            encoded_sequences[i] += [default_mapping] * (max_sequence_length - len(encoded_sequences[i]))

    return np.array(encoded_sequences), max_len


# ----------------- HELPER FUNCTIONS -------------------#

@staticmethod
def de_novo_assembly(df_sequences, chunk_size=100):
    kmer_size = 25
    g = nx.Graph()

    sequences = df_sequences['Sequence']

    for chunk_start in range(0, len(sequences), chunk_size):
        chunk_end = min(chunk_start + chunk_size, len(sequences))
        chunk_sequences = sequences.iloc[chunk_start:chunk_end]

        seq = chunk_sequences.iloc[0]

        kmer_to_vertex = {}
        for sequence in chunk_sequences:
            for i in range(len(seq) - kmer_size + 1):
                kmer = seq[i:i + kmer_size]
                if kmer not in kmer_to_vertex:
                    kmer_to_vertex[kmer] = len(kmer_to_vertex)
                    g.add_node(kmer_to_vertex[kmer])

        for sequence in chunk_sequences:
            for i in range(len(seq) - kmer_size):
                kmer1 = seq[i:i + kmer_size]
                kmer2 = seq[i + 1:i + kmer_size + 1]
                v1 = kmer_to_vertex[kmer1]
                v2 = kmer_to_vertex[kmer2]
                g.add_edge(v1, v2)

    data = to_networkx(g)
    edge_index = torch.tensor(list(data.edges)).t().contiguous()
    x = torch.eye(len(kmer_to_vertex))

    return data, edge_index, x, kmer_to_vertex


def _extract(sequence, features, task):
    X_train = sequence['Sequence']

    if task == 'annotation':
        y_train = sequence['Description']['Gene_Info']['seq_id'], features['feature_type'], features['attributes']
    elif task == 'prediction':
        y_train = features['start'], features['end'], features['strand']

    return X_train, y_train


def save_model(file_path, model):
    with open(file_path, 'wb') as file:
        pickle.dump(model, file)
        print(f"Model saved using pickle to {file_path}")


def load_model(file_path):
    with open(file_path, 'rb') as file:
        model = pickle.load(file)
        print(f"Model loaded using pickle from {file_path}")
    return model


def plot_training_history(history):
    plt.figure(figsize=(12, 6))

    plt.subplot(1, 2, 1)
    plt.plot(history.history['accuracy'])
    plt.plot(history.history['val_accuracy'])
    plt.title('Model Accuracy')
    plt.xlabel('Epoch')
    plt.ylabel('Accuracy')
    plt.legend(['Train', 'Validation'], loc='upper left')

    plt.subplot(1, 2, 2)
    plt.plot(history.history['loss'])
    plt.plot(history.history['val_loss'])
    plt.title('Model Loss')
    plt.xlabel('Epoch')
    plt.ylabel('Loss')
    plt.legend(['Train', 'Validation'], loc='upper left')

    plt.tight_layout()
    plt.show()


if __name__ == "__main__":
    read_fasta_done = False

    df_sequence, _ = read_fasta(r"C:\Users\sarth\OneDrive\Documents\Drive\GCF_000001405.40_GRCh38.p14_genomic.fna")
    read_fasta_done = True

    if read_fasta_done:
        df_features = read_gff(r"C:\Users\sarth\OneDrive\Documents\Drive\genomic.gff")

    data, edge_index, x, kmer_to_vertex = DeBruijnGNN.de_novo_assembly(df_sequence)

    trained_model = DeBruijnGNN.train_gnn(data, edge_index, x)

    assembled_genome = DeBruijnGNN.predict(df_sequence, trained_model, data, kmer_to_vertex)

    geneModel, geneHist, geneTest_acc, geneTest_loss = train_model(assembled_genome, df_features, epochs, batch_size,
                                                                   task='prediction')
    plot_training_history(geneHist)
    predict_and_annotate(geneModel, assembled_genome)

    funcModel, funcHist, funcTest_acc, funcTest_loss = train_model(assembled_genome, df_features, epochs, batch_size,
                                                                   task='annotation')
    plot_training_history(funcHist)
    predict_and_annotate(funcModel, assembled_genome)

    save_model("C:/Users/sarth/OneDrive/Documents/Drive/assemblyModel.pkl", geneModel)
    save_model("C:/Users/sarth/OneDrive/Documents/Drive/analysisModel.pkl", funcModel)
