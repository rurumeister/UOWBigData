# Data Processing & Analytics with Apache Spark

#### Processing Massive Data

- one solution to speed up the computation is parallelism (or distributed computing)
- this is where MapReduce comes in. One pre-eminent model for parallel computing.
  How does MapReduce work?

1. Map Stage: performs simple mapping-alike operations to produce key-value pairs.
2. Intermediate Stage: merges key-value pairs per key.
3. Reduce: performs aggregation operations on the merged key-value pairs.
   - MapReduce uses (key, value) as the basic data structure.

### MapReduce:

#### Computing Selections in MapReduce:

- Map function: for each tuple t in R, test if it satisfies C. If so, the mapper produces key-value pair (t,t). Otherwise, it produces nothing.
- Reduce function: the identity function.

#### Relational-Algebra Operations in MapReduce:

1. Natural Join:

   Computing Natural join in MapReduce:</br>
   a. Map function: For each tuple (a, b) in R[A, B], produce the key value pair (b, (R,a)). For each tuple in (b, c) in S[B, C], produce the key-value pair (b, (S, c)). </br>
   b. Reduce function: Each key value b will be associated with a list of pairs that either form (R,a) or (S,c). Construct all tuples (a,b,c) if both (R,a) and (S,c) are present in the list that b is associated with.

   Conclusion: All common SQL queries can be implemented with MapReduce.

#### kNN Classifier in MapReduce:

Idea:

1. Mapper rerturns <key1, val1> where key1 is a a movie name and val1 is the distance to the unknown movie
2. Reducer returns <key2, val2> where key is null and val2 ios a list of k nearest movies (to the unknown movie) and the distances
3. Voting function is used based on val2 to determine the class for the unknow movie.

### Using Spark:

#### Spark's Abstractions and APIs

1. Dataframe - high level structured abstraction.
   - can be stored in single or multiple machines.
   - each row is like an object of the Spark's Row type.
2. Resilient Distributed Dataset (RDD) - low level abstraction.
   - often used for creating a DF.
3. Both DF and RDD are immutable. Instead of altering elements in a DF or RDD, you create a new one.

#### Transformation and Action:

1. End users operate DF/RDD as if data is on a single com
2. Two kinds of operations: Transformations and Actions.

- Transformations create another DF/RDD.
- Actions produce computational resules
- Spark app can be viewed as a Direct Acyclic Graph of transformations and actions.

3. Lazy evaluation: Spark doesn't evaluted the DF/RDD until an action is called.

- More flexible than the MapReduce model in developing data analytics pipeline.

## Summary:

#### MapReduce Model:

1. powerful computational model for processing massive data.
2. Hadoop's MapReduce Framework

#### Spark Model:

1. Workflow model consisting of a series of transformations and actions.
2. Spark's rich set of APIs and in-memory computation
