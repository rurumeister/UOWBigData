## How to Implement a Decision Tree in Python - Classification by Splitting Data (Greedy Algorithm)

- Root node - based on condition, we will split the data into two or more branches
- Decision node - has two or more branches
- Leaf node - has no children

To calculate Information Gain:

1. Need to find info of root state. We have the whole data with us.

- Entropy = -p(0.5)log2p(0.5) - p(0.5)log2p(0.5)
- Entropy = 1 (max entropy)
- Calculate entropy of each attribute

2. Subtract combined entropy of each attribute from root entropy to get information gain

- Information gain = Entropy(root) - Entropy(attribute)

- Compares every possible split and takes the one that gives the highest information gain

## Gini Index, InfoGain, Gain Ratio

1. Gini Index uses Probability:

```
   P(sizeOfClass| avg) = 2/8
   P(sizeOfClass| avg & studentSat| low) = 1/2
   P(sizeOfClass| avg & studentSat| high) = 1/2
   gini index = 1 - (1/2)^2 + (1/2)^2 = 0.5

   P(sizeOfClass| small) = 4/8
   P(sizeOfClass| small & studentSat| low) = 2/4
   P(sizeOfClass| small & studentSat| high) = 2/4
   gini index = 1 - (2/4)^2 + (2/4)^2 = 0.5

   gini index for size of class = (2/8 _ 0.5) + (2/8 _ 0.5) + (4.8 \* 0.5) = 0.5
```

2. InfoGain uses Entropy (Shanon Entropy):

```
   Step 1: Calculate Entropy of Class
   E(buysComputer = "yes") = -9/14 X log2(9/14)
                           = 0.410
   E(buysComputer = "no") = -5/14 x log2(5/14)
                          = 0.53
   E(buysComputer) = E(buysComputer = "yes") + E(buysComputer = "no")
                   = 0.940
   Step 2: Calcualte Entropy for Feature

   E(buysComputer="yes", student="yes") = -6/7 * log2(6/7) = 0.191
   E(buysComputer="no", student="yes") = -1/7 * log2(1/7) = 0.401
   E(buysComputer="yes", student="no") = -3/7 * log2(3/7) = 0.524
   E(buysComputer="no", student="no") = -4/7 * log2(4/7) = 0.461

   E(buysComputer, student ="yes") = E(buysComputer="yes", student="yes") + E(buysComputer="no", student="yes")
                                    = 0.191 + 0.401 = 0.591
  E(buysComputer, student="no") = E(buysComputer="yes", student="no") + E(buysComputer="no", student="no")
                                = 0.524 + 0.461 = 0.985

   Step 3: Calculate InfoGain
   IG(buysComputer, student) = E(buysComputer) - [7/14 * E(buysComputer, student ="yes") + 7/14 * E(buysComputer, student="no") ]
                             = 0.940 - [7/14 * 0.591 + 7/14 * 0.985]
                             = 0.151
```

3. Gain Ratio

```
Contiues off of InfoGain
   Step 1: Calculate Split Information of each attribute
   Step 2: Calculate Gain Ratio
   GainRatio() = InfoGain() / SplitInformation()
```

#### 1. Import the necessary libraries

```python
import pandas as pd
import numpy as np
eps = np.finfo(float).eps
from numpy import log2 as log
```

#### 2. Load the data

```python
col_names = ['age', 'income', 'student', 'credit_rating', 'buys_computer']
df = pd.read_csv("data.csv", header=None, names=col_names)
df.head(10)
```

#### 3. Create Node Class

```python
class Node():
    def __init(self, feature_index=None, threshold=None, left=None, right=None, info_gain=None, value=None):
        '''constructor'''
        # for decision node
        self.feature_index = feature_index
        self.threshold = threshold
        self.left = left
        self.right = right
        self.info_gain = info_gain

        # for leaf node
        self.value = value #majority class of leaf node
```

#### 4. Create Decision Tree Class

```python
class DecisionTreeClassifier():
    def __init__(self, min_samples_split=2, max_depth=2):
        self.root = None
        self.min_samples_split = min_samples_split
        self.max_depth = max_depth
    def build_tree(self, dataset, curr_depth=0):
        X, Y = dataset[:, :-1], dataset[:, -1]
        nyum_+samples, n_features = np.shape(X)

        if num_samples >= self.min_samples_split and curr_depth <= self.max_depth:
            # find best split
            best_split = self.get_best_split(dataset, num_samples, n_features)
            # check if information gain is positive
            if best_split["info_gain"] > 0:
                # recur left
                left_subtree = self.build_tree(best_split["dataset_left"], curr_depth + 1)
                # recur right
                right_subtree = self.build_tree(best_split["dataset_right"], curr_depth + 1)
                # return decision node
                return Node(best_split["feature_index"], best_split["threshold"], left_subtree, right_subtree, best_split["info_gain"])
        # compute leaf node
        leaf_value = self.calculate_leaf_value(Y)
        # return leaf node
        return Node(value=leaf_value)h
    def get_best_split(self, dataset, num_samples, num_features):
        # dictionary to store the best split
        best_split = {}
        max_info_gain = -float("inf")

        # loop over all the features
        for feature_index in range(num_features):
            feature_values = dataset[:, feature_index]
            possible_thresholds = np.unique(feature_values)
            # loop over all possible thresholds of feature
            for threshold in possible_thresholds:
                #get current split
                dataset_left, dataset_right = self.split(dataset, feature_index, threshold)
                #check if childs are not null
                if len(dateset_left)> 0- and len(dataset_right) > 0:
                    y, n = len(dataset_left[:, -1]), len(dataset_right[:, -1])
                    # compute information gain
                    curr_info_gain = self.information_gain(dataset, dataset_left, dataset_right, y, n)
                    # update the best split if needed
                    if curr_info_gain > max_info_gain:
                        best_split["feature_index"] = feature_index
                        best_split["threshold"] = threshold
                        best_split["dataset_left"] = dataset_left
                        best_split["dataset_right"] = dataset_right
                        best_split["info_gain"] = curr_info_gain
                        max_info_gain = curr_info_gain
        # return best split
        return best_split

    def split(self, dataset, feature_index, threshold);
        dataset_left = np.array([row for row in dataset if row[feature_index] <= threshold])
        dataset_right = np.array([row for row in dataset if row[feature_index]>= threshold])

        return dataset_left, dataset_right

    def information_gain(self, parent, l_child, r_child, mode="entropy"):
        weight_l = len(l_child) / len(parent)
        weight_r = len(r_child) / len(parent)
        if mode === "gini":
            gain = self.gini_index(parent) - (weight_l * self.gini_index(l_child) + weight_r * self.gini_index(r_child))
        else:
            gain = self.entropy(parent) - (weight_l * self.entropy(l_child) + weight_r * self.entropy(r_child))
```

DT Pseudocode:
Define function(make_tree) which takes in Sample S and Attribute List A

1. Create Node N
2. If Sample are of the same class C,</br>
   a. return label (N) with (C);
3. If A is empty</br>
   a. return label (N) with the most common class C in S;</br>
4. Select a from A</br>
   a. if it's a categorical dataset,</br>
   with highest information gain, Label (N) with a;</br>
   b. if it's a continuous dataset,</br>
   with lowest gini index, label (N) with a;</br>
5. For each value v in a</br>
   a. Create a branch from Node N where a = v</br>
   b. Let (Sv)= subset of sample in S where a = v</br>
6. If (Sv) is empty</br>
   a. Attach a leaf node with the most majority class S to N.</br>
7. Else, attach node generated by make_tree(Sv, A-a)
8. Return N
