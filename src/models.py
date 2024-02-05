import pickle

def save_model(model, file_path):
    """
    Saves the model to the specified file path using pickle.

    Parameters:
    - model: The model to be saved.
    - file_path (str): The file path where the model will be saved.
    """
    with open(file_path, 'wb') as file:
        pickle.dump(model, file)
        print(f"Model saved using pickle to {file_path}")

def load_model(file_path):
    """
    Loads the model from the specified file path using pickle.

    Parameters:
    - file_path (str): The file path where the model is saved.

    Returns:
    - model: The loaded model.
    """
    with open(file_path, 'rb') as file:
        model = pickle.load(file)
        print(f"Model loaded using pickle from {file_path}")
    return model
