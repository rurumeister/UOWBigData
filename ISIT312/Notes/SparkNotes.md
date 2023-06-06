# Spark Notes
- MapReduce framework releases the developer from the distributing computing, trickiness, its Java API is too low-level (Google's introduction of MapReduce makes the processing of big data feasible and practical)
- Hive, Pig and other frameworks based on MapReduce can help

## Data Sharing in Hadoop
- Although Hadoop provides numerous abstractions for data access, data sharing is slow due to replication, serialisation and persistent storage IO

### Apache Spark is a powerful open-source distributed computing framework that provides fast and general-purpose data processing capabilities. It offers a range of features that make it popular for big data processing and analytics:

1. Speed: 
- Spark is significantly faster than MapReduce due to its in-memory processing capabilities. 
- MapReduce writes intermediate data to disk after each Map and Reduce operation, resulting in higher disk I/O and slower performance. 
- In contrast, Spark keeps data in memory, enabling faster data access and iterative processing. Spark's ability to cache and reuse data in memory reduces disk I/O and results in faster execution times.

2. Distributed Computing: Spark allows you to distribute data and computations across a cluster of machines, enabling parallel processing. It efficiently manages the distribution, scheduling, and fault tolerance of tasks across the cluster.

3. Fault Tolerance: Spark provides built-in fault tolerance mechanisms. It can recover from failures by reassigning tasks to different nodes in the cluster. This ensures that your data processing jobs can continue uninterrupted even in the presence of failures.

4. In-Memory Processing: Spark leverages in-memory data storage to cache and process data in memory, reducing disk I/O and accelerating data processing. This is particularly useful for iterative algorithms and interactive data analysis.

5. Rich APIs: Spark provides APIs in multiple programming languages, including Scala, Java, Python, and R. These APIs offer a wide range of functionality, allowing developers to perform various tasks such as data transformation, SQL queries, machine learning, graph processing, and more.

6. Flexible Data Processing: Spark supports various data processing techniques, including batch processing, real-time streaming, machine learning, graph processing, and interactive SQL queries. This versatility makes Spark suitable for a wide range of use cases and allows you to combine different processing techniques within a single application.

7. Integration with Big Data Ecosystem: Spark integrates well with other popular big data technologies such as Hadoop, Hive, HBase, Kafka, and more.

8. Machine Learning and Graph Processing: Spark includes libraries for machine learning (MLlib) and graph processing (GraphX), providing high-level APIs and algorithms for common tasks in these domains. This makes it convenient to perform advanced analytics and build machine learning models within the Spark framework.

9. Integration with Hadoop Ecosystem: 
- Spark integrates seamlessly with other components of the Hadoop ecosystem, such as HDFS, Hive, HBase, and more. 
- It can read data from and write data to various data sources, making it compatible with existing Hadoop infrastructure. 
- MapReduce is tightly coupled with Hadoop Distributed File System (HDFS) and requires more effort for integration with other tools.

10. Community and Ecosystem: 
- Spark has gained significant popularity and has a large and active open-source community. 
- Wide range of third-party tools, libraries, and connectors built around it, expanding its ecosystem. 
- MapReduce, while widely used historically, has seen a decline in adoption and community activity in recent years.

## Spark Architecture
1. Driver Program is the main Spark app that consists of the data processing logic
2. Executor is the JVM process taht runs on each worker node.
3. Task is a subcomponent of a data processing job.

|Driver       |     |Worker Node         |     |Worker Node   |   |
|-------------|-----|--------------------|-----|--------------|---|
|             | --> |Executor            | --> |Executor      | V |
|Spark Session| --> |Task Task           | --> |Task Task     |   |        
|             | <-- |Executor            | <-- |Executor      | ^ |

## Spark Architecture
High-level API of Spark eases the development of a data processing pipeline

## Resilient Distributed Data Sets (RDDs)
is the lowest level (and the oldest) data abstraction available to the users
```java
    // RDD can be created from a list
    val strings = "hello hello !".split(" ")

    //Creating RDD from a file by reading line-by-line
    val lines = spark.sparkContext.textFile("sales.txt")
    //lines: org.apache.spark.rdd.RDD[String] = sales.txt MapPartitionsRDD[1] at textFile at :24
    lines.collect()
    //res0: Array[String] = Array(bolt 45, bolt 5, drill 1, drill 1, screw 1, screw 2, screw 3)
    val pairs = lines.map(s => (s, 1))
    //pairs: org.apache.spark.rdd.RDD[(String, Int)] = MapPartitionsRDD[2] at map at :26)
    val counts = pairs.reduceByKey((a, b) => a + b)
    //counts: org.apache.spark.rdd.RDD[(String, Int)] = ShuffledRDD[3] at reduceByKey at :28)
    counts.collect()
```

## Datasets
- Dataset is a distributed collection of data.
- DataFrame is a Dataset of type Row
```java
    case class Person(name: String, age: Long)
    val caseClassDS = Seq(Person("Andy", 32)).toDS()
    caseClassDS.show()
    // +----+---+
    // |name|age|
    // +----+---+
    // |Andy| 32|
    // +----+---+
    caseClassDS.select($"name").show()
    // +-----+
    // | name|
    // +-----+
    // |James|
    // +-----+
```

## DataFrames
- DataFrame is a table of data with rows and columns
```java
    val dataFrame = spark.read.json("/bigdata/people.json")
    dataFrame.show()
    // +----+-------+
    // | age| name |
    // +----+-------+
    // |null|Michael|
    // | 30 | Andy |
    // | 19 | Justin|
    // +----+-------+
    dataFrame.printSchema()
    root
    // |-- age: long (nullable = true)
    // |-- name: string (nullable = true)
```

## SQL Tables/Views
- SQL can be used to operate on DataFrames
- A DataFrame dataFrame can be registered as SQL temporary view
people in the following way
```java
    dataFrame.createOrReplaceTempView("people")
    val sqlDF = spark.sql("SELECT * FROM people")
    sqlDF.show()
    // +----+-------+
    // | age| name |
    // +----+-------+
    // |null|Michael|
    // |30 |Andy |
    // |19 |Justin |
    // +----+-------+
```

- SQL Tables are logically equivalent to DataFrames
- A difference between SQL Tables and DataFrames is such that DataFrames are defined within the scope of a programming language while SQL Tables are defined within a database

```sql
    CREATE TABLE flights(
    DEST_COUNTRY_NAME STRING,
    ORIGIN_COUNTRY_NAME STRING, count LONG)
    USING JSON OPTIONS ( path '/mnt/defg/chapter-1-data/json/2015-summary.json')

    CREATE VIEW just_usa_view AS
    SELECT *
    FROM flights
    WHERE dest_country_name = 'United States'
```

### Managed versus unmanaged tables
- Creating unmanaged (external) table in Spark
    - Managed table is a table that stored data and metadata, it is equivalent to an internal table in Hive
    - Unmanaged table is a table that stores only data, it is equivalent to an external table in Hive

# Spark Operations
- Spark has built-in APIs for Java, Scala, and Python, and is also integrated
with R
- Scala is a Java-like programming language which unifies object-oriented
and functional programming

### Quick Start 
```java
textFile.map(line => line.split(" ").size).reduce((a, b) => if (a > b) a else b)
// res3: Int = 22
val wordCounts = textFile.flatMap(line => line.split("")).groupByKey(identity).count()
// wordCounts: org.apache.spark.sql.Dataset[(String, Long)] = [value: string, count(1): bigint]
wordCounts.show(2)
// +----------+--------+
// | value|count(1)|
// +----------+--------+
// | online| 1|
// | graphs| 1|
// +----------+--------+
// only showing top 2 rows
wordCounts.collect()
// res7: Array[(String, Long)] = Array((online,1), (graphs,1), (["Parallel,1), (["Building,1), (thread,1),
// (documentation,3), (command,,2), (abbreviated,1), (overview,1), (rich,1), (set,2), ...
```

# Spark Structured Data and Stream Processing
- Stream processing capability is lacked in Hadoop MapReduce framework due to
slowness of hard-disk IO
## Structured Processing
- Structured Streaming Processing suppose to provide fast, scalable, fault-tolerant, end-to-end exactly-once stream processing without the user
having to reason about streaming

```java
    val lines = spark.readStream
    .format("socket") // socket source
    .option("host", "localhost") // listen to the localhost
    .option("port", 9999) // and port 9999
    .load()
    import spark.implicits._
    val words = lines.as[String].flatMap(_.split(" "))
    val wordCounts = words.groupBy("value").count()
    val query = wordCounts.writeStream
    .outputMode("complete") // accumulate the counting result
    .format("console") // use the console as the sink
    .start()
```