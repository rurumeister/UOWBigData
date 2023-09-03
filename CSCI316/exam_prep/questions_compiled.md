# All Test questions compiled

## Big Data and Pre-processing concepts

    a. Why pre-processing can improve the quality of data pipeline?/ Why is pre-processing important in big data?
    Pre-processing is a set of machine learning techniques used prior to the mining of big data. It deals with domain
    understanding, data quality (missing values, redundancy, noise), data format (unstructured data), size of the data, data from varying sources and imbalanced data. It is necessary in order for our machine learning models to work as expected and to ensure that our accuracy is not affected by noise or outliers.

    b. What is the difference between noise and outliers?
    Noise is corrupted or distored data containing false information, Outliers however are observations/ data points
    that are distant from the majority observations/ data points.

    c. Why large-scale machine learning is challenging?
    Large-scale Machine Learning is challenging due to the immense scale of the data that is being processed through the
    models. It can cause high & intense CPU usage, too much data can cause the model to not perform well as traditional models may not perform well with the data, also, ensuring data quality may be challenging.

    d. Summarise the difference between pre-processing and data mining?
    Pre-processing is a set of data preparation techniques before the act of data mining.
    Data mining is the core process of discovering patterns, relationships and valuable information from a dataset.

    e. Advantages and disadvantages of data aggregation?
    Advantages of data aggregation reduced data volume, simplified analysis, and improved performance
    Disadvantages include loss of detail and potential misinterpretation.

    f. Explain undersampling and oversampling, and when will you apply them?
    Undersampling is the act of decreasing the amount of instances in the majority class to balance the class distribution when the majority class overwhelms the minority class.
    Oversampling is the act of increasing the amount of instances in the minority class to balance class distribution when the majority class overwhelms the minority class.

    g. Explain why we cannot reuse the training data for testing in data mining?
    We cannot reuse training data for testing data in data mining as it may be inaccurate and skewed as we have input the same data we used for training in testing causing overfitting and bias.

    h. Explain the concept of feature selection and feature generation, and in what situation to use each method.
    Feature selection is used when we decide to use a feature that has higher correlation (relevancy) to the class we are pointing at.
    Feature generation is necessary when there is no feature that satisfies our specific use case/  capture important information for our problem.

    i. Explain why we need to convert strings to numerical values in data mining. Describe a concrete example to demonstrate the advantage(s) of one-hot encoding compared with the direct conversion of strings to numerical values.
    We need to convert strings to numerical values in data mining as some models do not accept strings as input.

    Advantage of one-hot encoding vs direction conversion of strings to numerical values is that there will not be any loss in correctness. One-hot encoding creates a new feature per non-duplicate value allowing for accurate features. It also preserves categorical information.

    j. Explain the advantages of stratified sampling over standard random sampling.
    Stratified sampling allows for us to use the strata to build sampling over data.
    On the other hand, standard random sampling may cause innacuracy in data (as it may unfairly choose which to sample) causing model innacuracy. Improved precision and reduced bias.

## Python Programming

    a. Implement from scratch a Python function that takes a list of string values and returns
    Assume that a Python list is defined as follows:
    X = list(range(10))
    Using the list comprehension method, implement another list named Y which contains all the even numbers in X. Present the Python code of your implementation. Also present the output of the command “print(Y)”.

```python
def get_even(X):
   Y = [x for x in X if x % 2 == 0]
   print(Y)
```

    b. Implement from scratch a Python function for simple numerical encoding. This function takes a list of string values as input and returns a vector of integers as output. Write down the Python code.

```python
def numerical_encoding(X):
   non_duplicate_list = list(set(X))
   encoded_dict = {}
   encoded_vector = []
   for i, value in enumerate(non_duplicate_list):
      encoded_dict[value] = i
   encoded_vector[encoded_dict[value] for value in X]
   return encoded_vector
```

    c.  Implement from scratch a Python function that takes a list of string values and returns
    Assume that a Python list is defined as follows:
    X = [1, 2, 3, 4]
    Write the Python code to filter the numbers bigger than 2 in X.

```python
   def bigger_that_two(X):
      Y = [x for x in X if x > 2]
      return Y
```

    d. Implement from scratch a Python function to compute the Gini index of a list. This function takes a list of
    categorical values as input and returns the Gini index as output. Write down the Python code.

```python
   def gini_index(X):
     # Step 1: Calculate the frequency of each category
     category_counts = {}
     for category in X:
        if category in category_counts:
            category_counts[category] += 1
        else:
            category_counts[category] = 1

     # Step 2: Calculate the probability of each category
     total_samples = len(X)
     probabilities = [count / total_samples for count in category_counts.values()]

     # Step 3: Compute the Gini index
     gini = 1.0
     for probability in probabilities:
        gini -= probability ** 2

     return gini
```

    e. Given a list named X which contains words (as strings), implement a Python function to compute the frequencies of words in X. Write down the Python code.

```python
def word_freq(X):
   freq_dict = {}
   for value in X:
      if value in freq_dict:
         freq_dict[value] += 1
      else:
         freq_dict[value] = 1
   return freq_dict
```

    f. A list named Y contains numerical values (in float type). But some elements of Y have the value “None” (i.e., a missing value). Implement a Python function which replaces each missing value with the mean value of Y. Write down the Python code.

## Decision Tree

1. Present the process of using InfoGain to split the data set according to the “student” feature. Detail the steps in your computation process. (SE 2021 S3, SE 2023 S1,)

```
Step 1: Entropy of Class
E(buys_computer = "yes") = -9/14 * (log2(9/14)) = 0.410
E(buys_computer = "no") = -5/14 * (log2(5/14)) = 0.531
E(buys_computer) = E(buys_computer = "yes") + E(buys_computer = "no")
                 = 0.410 + 0.531 = 0.94

Step 2: Calculate Entropy of Student Feature
Students_yes = 7, Students_no = 7
E(buys_computer = "yes", student="yes") = -6/7 * (log2(6/7)) = 0.191
E(buys_computer = "no", student="yes") = -1/7 * (log2(1/7)) = 0.401
E(buys_computer = "yes", student="no") = -3/7 * (log2(3/7)) = 0.524
E(buys_computer = "no", studnet="no") = -4/7 * (log2(3/7)) = 0.461
E(buys_computer, student = "yes") = 0.191 + 0.401 = 0.592
E(buys_computer, student = "no") = 0.524 + 0.461 = 0.985

Step 3: Calculate Infogain
Infogain(buys_computer, student) = 0.94 - [7/14 * 0.592 + 7/14 * 0.985]
                                 = 0.151
The conditional Shannon Entropy is [7/14 * 0.592 + 7/14 * 0.985], thus the Info Gain is 0.151
```

2. Assume that you are given a set of records as shown in the following table, where the last column contains
   the target variable. Present the procedure of using Gain Ratio to identify which attribute should be split. You
   need to show all steps of your calculation in detail. (FE 2021 S1)

```
Gain Ratio
Step 1: Find Entropy of Student satisfaction
E(StudSat | yes) = -(4/8)* log2(4/8) = 0.5
E(StudSat | no) = 0.5
WeightedE(StudSat) = 1

Step 2: Find Entropy of Lecturer Exp and Prog Sub features:
a. Lecturer Exp
E(StudSat | yes, Lecturer Exp | Strong) = -(3/4) * (log2(3/4)) = 0.311
E(StudSat | no, Lecturer Exp | Strong) = -(1/4) * (log2(1/4)) = 0.5
E(StudSat | yes, Lecturer Exp | Weak) = -(3/4) * (log2(3/4)) = 0.311
E(StudSat | no, Lecturer Exp | Weak) = -(1/4) * (log2(1/4)) = 0.5
E(StudSat, LecturerExp | Strong) = 0.811
E(StudSat, LecturerExp | Weak) 0.811
WeightedE(LecturerExp) = [4/8 * 0.811 + 4/8 * 0.811] = 0.811

b. Programming Subj
E(StudSat | yes, Prog Sub | yes) = -(2/4) * (log2(2/4)) = 0.5
E(StudSat | no, Prog Sub | yes) = -(2/4) * (log2(2/4)) = 0.5
E(StudSat | yes, Prog Sub | no) = -(2/4) * (log2(2/4)) = 0.5
E(StudSat | no, Prog Sub | no) = -(2/4) * (log2(2/4)) = 0.5
E(StudSat | yes, Prog Sub | yes) = 1
E(StudSat | yes, Prog Sub| no) = 1
Weighted(ProgSub) = [4/8 * 1 + 4/8 + 1] = 1

Step 4: Find infogain:
Infogain(StudSat, ProgSub) = 0
Infogain(StudSat, LectuterExp) = 0.289

Step 5: SplitInformation
SplitInformation(LecturerExp) = -[P(Strong)* log2(P(Strong)) + P(Weak)*log2(P(Weak))]
SplitInformation(LecturerExp) = -[0.5 * log2(0.5) + 0.5*log2(0.5)]  = 1
SplitInformation(ProgSub) =  -[0.5 * log2(0.5) + 0.5*log2(0.5)]  = 1
SplitInformation(StudSat) = 1

Step 6: Gain ratio
Gain Ratio(LecturerExp) = IG(LecturerExp)/ SplitInfo(LecturerExp)
                        = 0.289 / 1
Gain Ratio(ProgSub) = IG(ProgSub)/ SplitInfo(ProgSub)
                        = 0/1 = 0

We pick Lecture Experience to Split as it has the higher gain ratio.
```

3. Assume that you are given a set of records as shown in the following table, where the last column contains the target variable. Present the procedure of using information gain to identify which attribute should be split. You need to show all steps of your calculation in detail. (FE 2021 S3)
   -2/3 \* (log2(2/3)) =

```
Step 1: Find Entropy of Class
E(Buy = "yes") = -4/8 * (log2(4/8))
               = -0.5 * -1 = 0.5
E(Buy = "no") = 0.5
E(Buy) = 1

Step 2: Find Entropy of Income feature
E(Buy ="yes", Income = "High") = -3/3 * (log2(3/3)) = 0
E(Buy ="no", Income = "High") = 0
E(Buy ="yes", Income = "Medium") = -2/3 * (log2(2/3)) = 0.389
E(Buy ="no", Income = "Medium") =  -1/3 * (log2(1/3)) = 0.528
E(Buy ="yes", Income = "Low") = -0/2 * (log2(0/2)) = 0
E(Buy ="no", Income = "Low") = 0
E(Buy, Income="High") = 0
E(Buy, Income="Medium") = 0.917
E(Buy, Income="Low") = 0
Weighted Entropy(Income) = [3/8 * 0 + 4/8 * 0.917 + 2/8 * 0] = 0.458

Step 3: Find Entropy of Age Feature
E(Buy ="yes", Age="Youth") = -1/3 * (log(1/3)) = 0.528
E(Buy ="no", Age="Youth") = -2/3 * (log(2/3)) = 0.389
E(Buy ="yes", Age="Middle age") = -1/2 * (log(1/2)) = 0.5
E(Buy ="no", Age="Middle age") = -1/2 * (log(1/2)) = 0.5
E(Buy ="yes", Age="Senior") = -2/3 * (log(2/3)) = 0.389
E(Buy ="no", Age="Senior") = -1/3 * (log(1/3)) =  0.528
E(Buy, Age="Youth") = 0.647
E(Buy, Age="Middle age") = 1
E(Buy, Age="Senior") = 0.917
Weighted Entropy(Age) = [3/8 * 0.647 + 2/8 * 1 + 3/8 * 0.917]
                     = 0.8365

Step 4: Find Info Gain
InfoGain(Buy, Income) = 1 - 0.458 = 0.542
InfoGain(Buy, Age) = 1 - 0.835 = 0.1635

Since the IG for attribute 'Income' is higher, we will pick this attribute to splite using info gain as the splitting criteria.

```

4. Why an ensemble classifier (such as a Random Forest) can enhance the performance of individual
   classifiers? (FE 2021 S1, FE 2021 S3) </br>
   Random Forest can enhance performance of individual classifiers such as DT because they increase the accuracy, can deal with data in sheer volume and run in parallel.</br>

5. Assume that you are given a set of records as shown in the following table, where the last column contains the target variable. Present the procedure of using Gini index to identify which attribute should be split. You need to show all steps of your calculation in detail. (FE 2022 D1)

```
Gini Index (used to identify what to split):

P(Size of Class="average") = 2/8
P(Size of Class="average", StudentSatisfaction = "low") = 1/2
P(Size of Class="average", StudentSatisfaction = "high") = 1/2
Gini Index = 1 - (1/2)^2 - (1/2)^2 = 0.5

P(Size of Class="large") = 2/8
P(Size of Class="large", StudentSatisfaction = "low") = 1/2
P(Size of Class="large", StudentSatisfaction = "high") = 1/2
Gini Index = 1 - (1/2)^2 - (1/2)^2 = 0.5

P(Size of Class="small") = 4/8
P(Size of Class="large", StudentSatisfaction = "low") = 1/2
P(Size of Class="large", StudentSatisfaction = "high") = 1/2
Gini Index = 1 - (1/2)^2 - (1/2)^2 = 0.5

Gini Index for sizeOfClass = (2/8 * 0.5) + (2/8 * 0.5) + (4/8 * 0.5) = 0.5

P(Lecturer Experience = "Strong") = 4/8
P(Lecturer Experience = "Strong", StudentSatisfaction="low") = 1/4
P(Lecturer Experience = "Strong", StudentSatisfaction="high") = 3/4
Gini Index = 1 - (1/4)^2 - (3/4)^2 = 0.475
P(Lecturer Experience = "Weak", StudentSatisfaction="low") = 3/4
P(Lecturer Experience = "Weak", StudentSatisfaction="high") = 1/4
Gini Index = 1 - (3/4)^2 - (1/4)^2 = 0.475
Gini Index for LecturerExperience = (4/8 * 0.475) + (4/8 *0.475) = 0.95

P(Programming Subj= "Yes") = 4/8
P(Programming Subj= "Yes", StudentSatisfaction="low") = 2/4
P(Programming Subj= "Yes", StudentSatisfaction="high") = 2/4
Gini Index = 1 - (2/4)^2 - (2/4)^2  = 0.5
Gini Index = 1 - (1/2)^2 - (1/2)^2 = 0.5
Gini Index for Programming Subject = (4/8 * 0.5) + (4/8 * 0.5) = 0.5

We will choose the lower gini index, however in this case, it is either Size of Class or Programming Subject
```

6. Assume that you are given a set of records as shown in the following table, where the last column contains the target variable. Present the procedure of using Gini index to identify which attribute should be split. You need to show all steps of your calculation in detail. (FE 2022 S1)

7. Present the pseudo-code of a decision tree induction algorithm for a data set with categorical and continuous features. You can also support the pseudo-code with explanations (FE 2022 S1)

```
define function make-tree with input Sample S and Attribute List A
1. Create node n;
2. If sample S has the same class C;
   a. return label(N) with C
3. If A is empty
   a. return label(N) with most common classs C in S;
4. Select (a) from A:
   a. If it's a categorical dataset,
      with highest infogain, Label (N) with a
   b. else if it's continuous dataset,
      with lowest giniIndex, Label (N) with a
5. For each value v in a:
   a. Grow a branch from N where a = v
   b. Let (Sv)= subset of S where a = v
6. If (Sv) is empty,
   a. Atach a leaf with the most majority class in S to N
7. Else attach node generated by make-tree(Sv, A-a)
8. return N;
```

## Naive Bayes Classifier and model evaluation

1. Explain the “zero count” problem for Naïve Bayes classifier, and use a concrete example to explain how this problem can be avoided.

- the count of records with a value of an attribute is zero when some class label is given
- Because usually the training dataset is large (i.e., the total count is large), adding 1 to each count causes minimum effect

2. In Naïve Bayesian classifiers, the numerical underflow and the zero count are two important issues. Explain these two issues and describe at least one common technique to overcome each issue.

- Numerical Underflow: If the number of attributes is large, the outputs of a Naïve Bayesian classifier are usually very small.
- Technique to overcome:
- Zero Count: the count of records with a value of an attribute is zero when some class label is given
- Technique to overcome: Laplace Smoothing (adding 1 to all counts.)

3. Why the Naïve Bayes classifier is efficient (e.g., compared with Decision Tree)?

- Naive Bayes Classifier is efficient compared to DT because it is generally simpler and faster than DT, Scalability, low parameter tuning.

4. Assume that a Bayesian classifier returns the following outcomes for a binary classification problem, which are sorted by decreasing probability values. P (resp., N) refers to a record belonging to a positive (resp., negative) class. </br>
   Answer the following questions for the above example, and present your calculation processes in detail. </br>
   • What are the true positive (recognition) rate and false negative (recognition) rate if setting the probabilistic classification threshold to ≥0.70?</br>

```
TP = 2, FN, 1, FP, 3, TN, 4
TPR = TP / TP + FN = 2 / 2 + 1 = 0.667
FPR = FP / FP + TN = 3 / 3 + 4 = 0.429

When the probabilistic classification threshold is >= 0.7, TPR is 0.667 and FPR is 0.429
```

• What is the smallest probabilistic classification threshold such that the precision is at least 60%? (SE 2021 S3)

```
Precision = TP / TP + FP
Recall = TP / TP + FN
F1 = 2 x prec x recall / prec x recall

Precision is set to 0.6 = 3/5 = 3/ 3 + 2
TP = 3, FP = 2
Smallest probabilistic threshold is 0.6
```

5. Assume that a Bayesian classifier returns the following outcomes for a binary classification problem, which are sorted by decreasing probability values. P (resp., N) refers to a record belonging to a positive (resp., negative) class. </br>
   What is the largest true positive rate when the false positive rate equals 0.4, and what is the smallest false positive rate when the true positive rate equals 0.8? Present the process of your calculation.
   (SE 2023 S1)

   ```
   TPR = TP / TP + FN
   FPR = FP / FP + TN
   Prec = TP / TP + FP
   Recall = TP / TP + FN
   F1 = 2 x prec x recall / prec x recall

   When FPR is 0.4:
   FPR = 2/5, FP = 2, TN = 3
   Probability is at 0.53
   TPR = 5/5+2 = 0.714
   Probability is at 0.54
   TPR = 4/4+2 = 0.667
   Largest TPR when FPR equals to 0.4 is 0.714 when probability is at 0.53

   When TPR is 0.8:
   TPR = TP / TP + FN = 8/10 = 4/5, TP = 4, FN = 1
   Probability is set to 0.6:
   FPR = 2/2+4 = 0.33
   Probability is set to 0.55:
   FPR = 1/1+4 = 0.2
   Smallest FPR when TPR is 0.8 is 0.2.
   ```

6. Use an example to illustrate the conditional independence assumption, and explain why it is important to the Naïve Bayes classifier. </br>
   Is a fundamental concept in the Naïve Bayes classifier. It assumes that the features used to describe an object are independent of each other given the class label. In other words, it assumes that knowing the value of one feature does not provide any information about the values of the other features when the class label is known. This assumption simplifies the probabilistic calculations involved in the classifier, making it computationally efficient.
7. Assume that a Bayesian classifier returns the following outcomes for a binary classification problem, which are sorted by decreasing probability values. P (resp., N) refers to a record belonging to a positive (resp., negative) class. </br>
   Answer the following questions for the above example, and present all steps of calculation in detail. </br>
   (a) What is the F score (i.e. F1 score) if setting the probabilistic classification threshold to 0.59? </br>
   (b) What is the highest probabilistic classification threshold such that the recall (sensitivity) is at least 80%? (FE 2021 S3)

## MapReduce

1. Explain how the MapReduce model can process the relational-algebra operation “inner join”. Use a concrete example to support your answer.

- Map Phase:
  - data is processed by Mappers, emitting key-value pairs, where key represents the join attribute and contains the rest of the record along with the indicared of the source dataset.
  - Key value pairs are shuffled together and grouped by key, ensuring that matching keys from both datasets are grouped together.
- Reduce Phase:
  - reducers process groups of key-value pairs with the same key.
  - perform the join operation by extracting values from R and S
  - result of join operation is generated for each group of matching keys.

2. Explain how the MapReduce model can support large-scale machine learning.</br>
   MapReduce model supports large-scale machine learning because of RDD, it can calculate and build models in parallel which means that it processes data faster.
3. Explain how the MapReduce model can process the vector-by-vector multiplication (i.e., dot product of two vectors).

4. Use an example to explain how the MapReduce model can process the outer join operation.

- Suppose we have two datasets R and S, that we want to perfor mand outer join based on a common attribute, such as a 'key'. Each dataset consists of key-value pairs.
  - In the Map phase, data from both datasets is processed, and key-value pairs are emitted. In the Reduce phase, Reducers process matching keys and perform the join operation, including NULL values for unmatched keys.

## Apache Spark

1. Why Apache Spark is suitable for large-scale machine learning?

- Because it has distributed computing which allows for parallel processing, resilient distributed datasets. ML libraries, and etc.
  Allowing to process large amounts of data, many supported libraries.

2. Why is Apache Spark more suitable for data-parallel computation than for model-parallel computation? Also use an example to support your answer.

3. Explain the major difference between Spark Data Frame and Pandas Data Frame as data structures?

4. Assume the following Data Frame is defined in the PySpark shell. Write down your codes in PySpark shell to fulfil the following operations. </br>
   Define a new DataFrame that includes on the “StockCode” and “Quantity” columns of df_RD. Count the unique values in the “InvoceNo” column, and compute the total order price (UnitPrice \* Quantity) per CustomerID. (SE 2021 S3)

5. Assume that a DataFrame named FlightsDF of flight statistics is defined in PySpark, with the following
   code processed.</br>
   Based on FlightsDF, write down the code in PySpark to implement the following operation: Find the country
   or countries with most international flights. (Note. An international flights has different original and destination
   countries.)(FE 2021 S1)

6. Assume that a DataFrame named FlightsDF of flight statistics is defined in PySpark, with the following code processed. Based on FlightsDF, write down the code in PySpark to implement the following operation: Find the country or countries with most domestic flights. (Note. A domestic flight has the same original and destination country.) (FE 2022 S1)

## TensorFlow and ANN

1. Why the softmax function is suitable for multiple classification but not regression?

- The softmax function is designed for multi-class classification tasks where the goal is to assign an input to one of several discrete classes and produce a probability distribution over these classes. It is not suitable for regression tasks, where the objective is to predict continuous numeric values without imposing a probability distribution on the output.

2. Why a classical Perceptron (i.e., a single layer of linear threshold units) is not preferable to use?</br>
   Classical perceptrons, with a single layer of linear threshold units, are limited in their ability to represent complex patterns, handle nonlinear relationships, and perform tasks beyond simple linear separations. As a result, they have largely been replaced by more powerful neural network architectures, such as multilayer perceptrons (feedforward neural networks) and deep neural networks, which can handle a wider range of machine learning and artificial intelligence tasks.
3. Implement a feedforward neural network by using the Keras API in TensorFlow for a multi-class
   classification problem. Assume that the data set has four numerical features and one target variable whose
   values are 1, 2 and 3. The network has one hidden layer with the sigmoid activation function. Present the
   Python code.

```python
import tensorflow as tf
from tensorflow import keras
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense
from tensorflow.keras.optimizers import Adam

# Define your dataset and labels (X_train, y_train, X_test, y_test)

# Define the number of classes
num_classes = 3

# Create a Sequential model
model = Sequential()

# Add a hidden layer with sigmoid activation function
model.add(Dense(32, input_shape=(4,), activation='sigmoid'))

# Add an output layer with softmax activation for multi-class classification
model.add(Dense(num_classes, activation='softmax'))

# Compile the model with appropriate loss and optimizer
model.compile(loss='sparse_categorical_crossentropy', optimizer=Adam(lr=0.001), metrics=['accuracy'])

# Train the model with your training data
model.fit(X_train, y_train, epochs=10, batch_size=32, validation_data=(X_test, y_test))

# Evaluate the model
test_loss, test_acc = model.evaluate(X_test, y_test)
print(f'Test accuracy: {test_acc}')

```

4. Implement a feedforward neural network by using the Keras API in TensorFlow for a regression problem. Assume that the data set has four numerical features and one numerical target variable. The network has one hidden layer with the sigmoid activation function. The number of neurons in the hidden layer is considered as a hyperparameter which you need to fine-tune. Write down the Python code of the implementation.
