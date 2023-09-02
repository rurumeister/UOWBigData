# Artificial Neural Network and Tensorflow

## What is TensorFlow?

a Python-friendly open source library for numerical computation that makes machine learning faster and easier

#### What is a Linear Threshold Unit (LTU)

- inputs of LTU are numbers
- each input connection is associated with a weight
- the LTU computes a weighted sum of its inputs
- if the sum is greater than a threshold value, the LTU outputs the positive class, otherwise it outputs the negative class

#### What is a Perceptron?

- a single layer of LTUs
- input neurons output whatever input they are fed
- a bias neuron, which outputs 1 all the time
- if we use logistic function (sigmoid) instead of a step function, it computes a continuous output

#### How is a Perceptron trained?

- Feed training instance x to each output neuron j at a time and compute its output y^j

#### What is a Multi-Layer Perceptron (MLP)?

- MLP is composed of one input layer, one or more layers of LTUs called hidden layers, and one final layer of LTUs called the output layer
- every layer except the output layer includes a bias neuron and is fully connected to the next layer
- when an ANN has two or more hidden layers, it is called a deep neural network (DNN)
- a sufficiently large MLP can approximate any continuous function

#### MLP - Cost Function?

- for regression problem, we use MSE as cost function
- for multi-classification problem , we use cross entropy as cost function

## Implement MLP in Keras for Classification

```python
X_train_full.shape #(60000, 28, 28)
X_train_full.dtype #dtype('uint8')
# Scale the pixel intensities down to the 0-1 range and convert them to floats, by dividing by 255.0
X_valid, X_train = X_train_full[:5000] / 255.0, X_train_full[5000:] / 255.0
y_valid, y_train = y_train_full[:5000], y_train_full[5000:]
# List of class names
class_names = ["T-shirt/top", "Trouser", "Pullover", "Dress", "Coat", "Sandal", "Shirt", "Sneaker", "Bag", "Ankle boot"]
class_names[y_train[0]]

# Creating an MLP using the Sequential API
model = keras.models.Sequential()
model.add(keras.layers.Flatten(input_shape=[28, 28]))
model.add(keras.layers.Dense(300, activation="relu"))
model.add(keras.layers.Dense(100, activation="relu"))
model.add(keras.layers.Dense(10, activation="softmax"))
# first layer flattens input from 2D to 1D
# second layer is a dense hidden layer with 300 neurons, specifiying activation="relu" means that it will use the ReLU activation function
# third layer is a dense hidden layer with 100 neurons
model.summary()

```
