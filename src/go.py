
df_sequence = read_fasta()

df_features = read_gff()

#Parameters
epochs, batch_size = 100, 32

# Perform de novo assembly to prepare the data
data, edge_index, x, kmer_to_vertex = DeBruijnGNN.de_novo_assembly(df_sequence)

# Train the DeBruijnGNN model
trained_model = DeBruijnGNN.train_gnn(data, edge_index, x)

# Make predictions using the trained model
assembled_genome = DeBruijnGNN.predict(sequences, trained_model, data, kmer_to_vertex)

#Genome Predictions
geneModel, geneHist, geneTest_acc, geneTest_loss = trainModel(assembled_genome, df_features, epochs, batch_size, task='prediction')
plot_training_history(geneHist)
predict_and_annotate(geneModel,  assembled_genome)

#Functional Annotation
funcModel, funcHist, funcTest_acc, funcTest_loss = trainModel(assembled_genome, df_features, epochs, batch_size, task='annotation')
plot_training_history(funcHist)
predict_and_annotate(funcModel, assembled_genome)