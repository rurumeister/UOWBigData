# All Test questions compiled

## Big Data and Pre-processing concepts

    a. Why pre-processing can improve the quality of data pipeline?/ Why is pre-processing important in big data?
    Pre-processing is a set of machine learning techniques used prior to the mining of big data. It deals with domain understanding, data quality (missing values, redundancy, noise), data format (unstructured data), size of the data, data from varying sources and imbalanced data. It is necessary in order for our machine learning models to work as expected and to ensure that our accuracy is not affected by noise or outliers.

    b. What is the difference between noise and outliers?
    Noise is corrupted or distored data containing false information, Outliers however are observations/ data points that are distant from the majority observations/ data points.

    c. Why large-scale machine learning is challenging?
    Large-scale Machine Learning is challenging due to the immense scale of the data that is being processed through the models. It can cause high & intense CPU usage, too much data can cause the model to not perform well as traditional models may not perform well with the data, also, ensuring data quality may be challenging.

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
    Stratified sampling allows for us to use the strata to build sampling over data. On the other hand, standard random sampling may cause innacuracy in data (as it may unfairly choose which to sample) causing model innacuracy. Improved precision and reduced bias.

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

2. Assume that you are given a set of records as shown in the following table, where the last column contains
   the target variable. Present the procedure of using Gain Ratio to identify which attribute should be split. You
   need to show all steps of your calculation in detail. (FE 2021 S1)

3. Assume that you are given a set of records as shown in the following table, where the last column contains the target variable. Present the procedure of using information gain to identify which attribute should be split. You need to show all steps of your calculation in detail. (FE 2021 S3)

4. Why an ensemble classifier (such as a Random Forest) can enhance the performance of individual
   classifiers? (FE 2021 S1, FE 2021 S3)

5. Assume that you are given a set of records as shown in the following table, where the last column contains the target variable. Present the procedure of using Gini index to identify which attribute should be split. You need to show all steps of your calculation in detail.

6. Assume that you are given a set of records as shown in the following table, where the last column contains the target variable. Present the procedure of using Gini index to identify which attribute should be split. You need to show all steps of your calculation in detail. (FE 2022 S1)

7. Present the pseudo-code of a decision tree induction algorithm for a data set with categorical and continuous features. You can also support the pseudo-code with explanations (FE 2022 S1)

## Naive Bayes Classifier and model evaluation

1. Explain the “zero count” problem for Naïve Bayes classifier, and use a concrete example to explain how this problem can be avoided.

2. In Naïve Bayesian classifiers, the numerical underflow and the zero count are two important issues. Explain these two issues and describe at least one common technique to overcome each issue.

3. Why the Naïve Bayes classifier is efficient (e.g., compared with Decision Tree)?

4. Assume that a Bayesian classifier returns the following outcomes for a binary classification problem, which are sorted by decreasing probability values. P (resp., N) refers to a record belonging to a positive (resp., negative) class. </br>
   Answer the following questions for the above example, and present your calculation processes in detail. </br>
   • What are the true positive (recognition) rate and false negative (recognition) rate if setting the probabilistic classification threshold to ≥0.70?</br>
   • What is the smallest probabilistic classification threshold such that the precision is at least 60%? (SE 2021 S3)

5. Assume that a Bayesian classifier returns the following outcomes for a binary classification problem, which are sorted by decreasing probability values. P (resp., N) refers to a record belonging to a positive (resp., negative) class. </br>
   What is the largest true positive rate when the false positive rate equals 0.4, and what is the smallest false positive rate when the true positive rate equals 0.8? Present the process of your calculation.
   (SE 2023 S1)

6. Use an example to illustrate the conditional independence assumption, and explain why it is important to
   the Naïve Bayes classifier.

7. Assume that a Bayesian classifier returns the following outcomes for a binary classification problem, which are sorted by decreasing probability values. P (resp., N) refers to a record belonging to a positive (resp., negative) class. </br>
   Answer the following questions for the above example, and present all steps of calculation in detail. </br>
   (a) What is the F score (i.e. F1 score) if setting the probabilistic classification threshold to 0.59? </br>
   (b) What is the highest probabilistic classification threshold such that the recall (sensitivity) is at least 80%? (FE 2021 S3)

## MapReduce

1. Explain how the MapReduce model can process the relational-algebra operation “inner join”. Use a concrete example to support your answer.

2. Explain how the MapReduce model can support large-scale machine learning.

3. Explain how the MapReduce model can process the vector-by-vector multiplication (i.e., dot product of two vectors).

4. Use an example to explain how the MapReduce model can process the outer join operation.

## Apache Spark

1. Why Apache Spark is suitable for large-scale machine learning?

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

2. Why a classical Perceptron (i.e., a single layer of linear threshold units) is not preferable to use?

3. Implement a feedforward neural network by using the Keras API in TensorFlow for a multi-class
   classification problem. Assume that the data set has four numerical features and one target variable whose
   values are 1, 2 and 3. The network has one hidden layer with the sigmoid activation function. Present the
   Python code.

4. Implement a feedforward neural network by using the Keras API in TensorFlow for a regression problem. Assume that the data set has four numerical features and one numerical target variable. The network has one hidden layer with the sigmoid activation function. The number of neurons in the hidden layer is considered as a hyperparameter which you need to fine-tune. Write down the Python code of the implementation.