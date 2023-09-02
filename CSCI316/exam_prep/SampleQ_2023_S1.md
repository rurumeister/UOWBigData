# Closed-Book Sample Exam Questions Session 1 2023

## Question 1: Big Data and pre-processing Concepts

a. Summarise the difference between pre-processing and data mining.

    1. The difference between pre-processing and data mining is that pre-processing is the process of preparing data for data mining. Data mining is the process of analysing data to find patterns and relationships.

b. What is the difference between noise and outliers?

    Noise:
    - Is corrrupted or distorted data containing false information and can be difficult to distinguish between outliers and noise.
    - Requires data cleaning to remove noise.
    Outliers:
    - An observation that is distant from other observations.
    - May be due to variability in the measurement or it may indicate experimental error.
    - Can cause problems in statistical analyses.

## Question 2: Python Programming

a. Implement from scratch a Python function that takes a list of string values and returns
Assume that a Python list is defined as follows:
X = [1, 2, 3, 4]
Write the Python code to filter the numbers bigger than 2 in X.

```python
def filter_list(x):
    list = []
    for i in x:
        if i > 2:
            list.append(i)
    return list

```

b. Implement from scratch a Python function for simple numerical encoding. This function takes a list of string values as input and returns a vector of integers as output. Write down the Python code.

```python
 def numerical_encoding(x):
    encoded_dict = {}
    encoded_vector = []
    for i in x:
        if i not in encoded_dict:
            encoded_dict[i] = len(encoded_dict)
        encoded_vector.append(endcoded_dict[i])
    return encoded_vector
```

## Question 3: Decision Tree

Suppose you are designing a DT classifier to processing the following data set:

```
   +----------------------+-------------+-------------------------+
   |RID|AGE       |INCOME|STUDENT|CREDIT_RATING|CLASS_BUY_COMPUTER|
   +---+----------+------+-------+-------------+------------------+
   |1  |youth     |high  |no     |fair         |no
   |2  |youth     |high  |no     |excellent    |no
   |3  |middleaged|high  |no     |fair         |yes
   |4  |senior    |medium|no     |fair         |yes
   |5  |senior    |low   |yes    |fair         |yes
   |6  |senior    |low   |yes    |excellent    |no
   |7  |middleaged|low   |yes    |excellent    |yes
   |8  |youth     |medium|no     |fair         |no
   |9  |youth     |low   |yes    |fair         |yes
   |10 |senior    |medium|yes    |fair         |yes
   |11 |youth     |medium|yes    |excellent    |yes
   |12 |middleaged|medium|no     |excellent    |yes
   |13 |middleaged|high  |yes    |fair         |yes
   |14 |senior    |medium|no     |excellent    |no
   +---+----------+------+-------+-------------+------------------+
```

Present the process of using InfoGain to split the data set according to the “student” feature. Detail the steps in your computation process.</br>
An answer template:</br>
i. Step 1: before the split, the Shannon entropy is `<insert the formula and value>`.</br>
ii. Step 2: after the split, for the case “student=yes”, the Shannon entropy is `<insert the formula and value>`; for the case “student=no”, the Shannon entropy is `<insert the formula and value>.`

    Step 1: Calculate Shannon Entropy before split
    Formula is as such
    H(S)= -p1log2p1 - p2log2p2
    p1 = number of 'yes' / total number of samples
    p2 = number of 'no' / total number of samples
    H(S) = -9/14log2(9/14) - 5/14log2(5/14)
    H(S) = 0.940

    Step 2: Calculate Shannon Entropy after split
    a) For "student = yes"
    p1(yes) = Number of 'yes' / total number of samples = 7/8
    p2(yes) = Number of 'no' / total number of samples = 1/8
    H(S | "student = yes") = -7/8log2(7/8) - 1/8log2(1/8)
    H(S | "student = yes") = 0.543
    b) For "student = no"
    p1(no) = Number of 'yes' / total number of samples = 3/7
    p2(no) = Number of 'no' / total number of samples = 4/7
    H(S | "student = no") = -3/7log2(3/7) - 4/7log2(4/7)
    H(S | "student = no") = 0.985

    Step 3: Calculate Information Gain
    IG = H(S) - H(S|A)
    IG = 0.940 - (8/14 * 0.543 + 6/14 * 0.985)
    IG = 0.151

## Question 4: NB and model evaluation

What is Naive Bayes Classifier:

a. Why is the Naive Bayes classifier is efficient (e.g. in comparison to DT)?

- Naive Bayes Classifier is efficient because it is simple and fast to predict class of test data set. It also performs well in multi-class prediction.
- It is also known to outperform even highly sophisticated classification methods.
- Simplicity and Speed: NB is a relatively simple and lightweight classification algorithm. Compultationally efficient and has low memory requirements. DT can become complext and computatuonally expensive.

(c) Assume that a Bayesian classifier returns the following outcomes for a binary classification problem, which are sorted by decreasing probability values. P (resp., N) refers to a record belonging to a positive (resp., negative) class.

```
Tuple #     Class   Probability
1           P        0.90
2           P        0.80
3           P        0.70
4           N        0.60
5           P        0.55
6           N        0.54
7           P        0.53
8           N        0.51
9           N        0.50
10          N        0.40
```

What is the largest true positive rate when the false positive rate equals 0.4, and what is the smallest false positive rate when the true positive rate equals 0.8? Present the process of your calculation.

a. Largest True positive rate when the false positive rate equals 0.4

```
If 0.4 false positive rate is needed:
FPR = FP / (FP + TN) = 0.4
Set True Positive Rate threshold to 0.53:
False Positive = 2, True Negative = 3
FPR = 2 / (2 + 3) = 0.4
TPR when threshold is 0.53
True Positive = 5, False Negative = 0
TPR = 5 / (5 + 0) = 1
Hence, the largest true positive rate when the false positive rate equals 0.4 is 1.
```

b. Smallest false positive rate when the true positive rate equals 0.8

```
FPR = FP / (FP + TN) = 0.8
Set True Positive Rate threshold to 0.55:
False Positive = 4, True Negative = 1
FPR = 4 / (4 + 1) = 0.8
TPR when threshold is 0.55
False Postiive = 1, True Negative = 4
FPR = 1 / (1 + 4) = 0.2
Hence, the smallest false positive rate when the true positive rate equals 0.8 is 0.2.
```

## Question 5: MapReduce Model

Explain how the MapReduce model can process the vector-by-vector multiplication (i.e. product of two vectors)

MapReduce can process the vector-by-vector multiplication by using the MapReduce model to perform the following steps:

1. Map: The map function will take in the two vectors and output the key-value pairs of the index and the value of the vector.
2. Shuffle: The shuffle function will group the key-value pairs by the index.
3. Reduce: The reduce function will take in the key-value pairs and output the key-value pairs of the index and the product of the two values.

## Question 6: PySpark and Spark MLlib
