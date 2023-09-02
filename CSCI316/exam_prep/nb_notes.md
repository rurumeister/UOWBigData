# Naive Bayes Notes

## What is Naive Bayes Classifier:

- Naive Bayes is a classification algorithm based on Bayes' Theorem.
- It is called Naive Bayes because it assumes that the presence of a particular feature in a class is unrelated to the presence of any other feature.
- Performance: A simple Bayesian classifier (Naive Bayes) is competitive with state-of-the-art classifiers.
- Incremental: Each training example can incrementally increase/decrease the probability of a hypothesis.

#### Classification Concepts:

1. Given a set of records (described by a sequence of attributes, the last one is the attribute of interest, called a class). The rest are called features.
2. Given a new record where Y is unknown, classifcation is to predict which class this record falls into.

#### Prior and Posterior probabilities:

1. Before evidence is obtained, the probability of a hypothesis is called prior probability.

- P(a) the prior prob that proposition is true
- P(rain) = 0.1

2. After evidence is obtained, the probability of a hypothesis is called posterior probability.

- P(a|e) the posterior prob that proposition is true given evidence e
- P(rain|cloudy) = 0.8

#### Bayes' Theorem:

- The conditional probability of event C ocurring, given event A is:
  P(C|A) = P(A n C) / P(A)
- Bayes' Theorem for two events:
  P(C|A) = P(A n C)/ (A) = P(A|C) \* P(C) / P(A) </br>
  Example:

#### Naive Bayes Classifier:

- To apply Bayes theorem to classification, one main problem is the number of combinations of attribute values.
- Naive Bayes classifier assumes that the effect of the value of a predictor (x) on a given class (y) is independent of the values of other predictors.

Dataset Example:

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

1. Let C1 correspond to the class buys_computer = "yes" and C2 correspond to the class buys_computer = "no". </br>

2. Let X denote (age = youth, income = medium, student = yes, credit_rating = fair) </br>

3. First, the prior probability of each class can be computed based on the training tuples: </br>
   P(C1) = 9/14 = 0.643 </br>
   P(C2) = 5/14 = 0.357 </br>

4. Compute the conditional probabilities of attributes on the class labels:
   P(age = youth | C1) = 2/9 = 0.222 </br>
   P(age = youth | C2) = 3/5 = 0.6 </br>
   p(income = medium | C1) = 4/9 = 0.444 </br>
   p(income = medium | C2) = 2/5 = 0.4 </br>
   p(student = yes | C1) = 6/9 = 0.667 </br>
   p(student = yes | C2) = 1/5 = 0.2 </br>
   p(credit_rating = fair | C1) = 6/9 = 0.667 </br>
   p(credit_rating = fair | C2) = 2/5 = 0.4 </br>

Naive Bayes reasoning:

- Using those probabilities, obtain:

```
  P(X| buys_computer = yes) = P(age = youth | C1)
                            * P(income = medium | C1)
                            * P(student = yes | C1)
                            * P(credit_rating = fair | C1)
                            = 0.222 * 0.444 * 0.667 * 0.667 * 0.643
                            = 0.039
  P(X| buys_computer = no) = P(age = youth | C2)
                           * P(income = medium | C2)
                           * P(student = yes | C2)
                           * P(credit_rating = fair | C2)
                           = 0.6 * 0.4 * 0.2 * 0.4 * 0.357
                           = 0.017
```

- Finally:

```
  P(X| buys_computer = yes)P(buys_computer = yes) = 0.039 * 0.643 = 0.025
  P(X| buys_computer = no)P(buys_computer = no) = 0.017 * 0.357 = 0.006
```

Therefore, the classifier predicts buys_computer = yes

## Simple NB Implementation:

```python
def loadDataSet():
    postingList= [['my', 'dog', 'has', 'flea','problems', 'help', 'please'],['maybe', 'not', 'take', 'him','to', 'dog', 'park', 'stupid'],['my', 'dalmation', 'is', 'so','cute', 'I', 'love', 'him'],['stop', 'posting', 'stupid','worthless', 'garbage'],['mr', 'licks', 'ate', 'my', 'steak', 'how', 'to', 'stop', 'him'],['quit', 'buying', 'worthless', 'dog', 'food', 'stupid']]classVec= [0, 1, 0, 1, 0, 1] # 1 is abusive, 0 not
    return postingList, classVec
```

Pseudocode of training function of a Naive Bayes classifier:

    Calculate the number(or proportion of documents) in each class;
    for every training document:
        for each class:
            if a token appears in the document then incraese count for that token;
            increse the count for tokens;
        for each class:
            for each token:
                divide the token count by the total token count to get conditional probability of token in that class;
    return conditional probabilities for each class;

    * Note that only discrete randomvariables(e.g., categoricalattributes) are considered in this example.
    * Continuous random variables are considered later.

```python
def trainNB0(trainMatrix, trainCategory):
    numTrainDocs = len(trainMatrix)
    numWords = len(trainMatrix[0])
    pAbusive = sum(trainCategory)/float(numTrainDocs)
    p0Num = zeros(numWords) # as numerator
    p1Num = zeros(numWords) # as numerator
    p0Denom = 0 # as denominator
    p1Denom = 0 # as denominator
    for i in range(numTrainDocs):
        if trainCategory[i] == 1:
            p1Num += trainMatrix[i]
            p1Denom += sum(trainMatrix[i])
        else:
            p0Num += trainMatrix[i]
            p0Denom += sum(trainMatrix[i])
    p1Vect = p1Num/p1Denom # change to log()
    p0Vect = p0Num/p0Denom # change to log()
    return p0Vect, p1Vect, pAbusive

def classifyNB0(vec2Classify, p0Vec, p1Vec, pAbusive):
    p1 = prod(power(p1Vec, vec2Classify))*pAbusive
    p0 = prod(power(p0Vec, vec2Classify))*(1.0 - pAbusive)
    if p1 > p0:
        return 1
    else:
        return 0
```

## Multiple Occurences:

- if a word appears more than once in a doc this is not accounted for in simple NB implementation.
- a bag of words can have multiple occurences of each word, whereas a set of words can only have one occurence of each word.

```python
def bagOfWords2Vec(vocabList, inputSet):
    returnVec = [0]*len(vocabList)
    for word in inputSet:
        if word in vocabList:
            returnVec[vocabList.index(word)] +=
    return returnVec

```

## Smoothing Zero Count:

- zero count: the count of records with a value of an attribute is zero when some class label is given.
- Common technique to overcome this is the Laplace smoothing, it adds 1 to all counts. </br>
  i.e. :With the Laplacian smoothing for the three quantities, adding 1 more tuple for each income value: the probabilities become 0.001(from 1/1003), 0.988(from 991/1003) and 0.011(form 11/1003).

## Continuous-Value Features:

- If we have continuous values, two common approaches are

1. Discretization/ bucketing/ Binning
2. Guassian Distribution

## Summary of Naive Bayes Classifier:

- Advantages:

1. Simple and easy to implement
2. Requires a small amount of training data to estimate the parameters necessary for classification
3. Extremely fast compared to more sophisticated methods

- Disadvantages:

1. The assumption of independent features. In practice, it is almost impossible that model will get a set of predictors which are entirely independent.
2. Zero frequency. If the category of any categorical variable is not seen in training data set even once, then model assigns a zero probability to that category and then a prediction cannot be made.
3. Practically, dependencies exist among variables.
   - E.g. Symptoms: fever, cough etc., Disease: lung cancer, diabetes, etc.
   - Dependencies among these cannot be modeled by Naive Bayes Classifier

- How to deal with these dependencies?

1. Bayesian Belief Networks
