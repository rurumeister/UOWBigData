# Question 1
Based on your own experience of using Apache Pig and Apache Spark in this subject, briefly explain:

(i) Similarity of data processing between Apache Pig and Apache Spark \
Both Apache Pig and Apache Spark allow for high-level processing frameworks that allow users to express data transformations and computations in a declarative manner. 
Abstract away from the complexities of distributed data processing and provide higher-level abstractions for data manipulation (when compared to Hadoop MapReduce). Both allow for parallel and distributed programing. 
Offer support for various data sources and formats, allowing users to read and process data from different sources.

(ii) Advantages of Apache Spark of Apache Pig:
1. Speed and Performance: Spark's in-memory processing capabilitiy speeds up data processing tasks vs. disk-based processing in Pig. (Spark caches data in memory)
2. Flexibiliy and Rich APIs: Spark provides a rich set of APIs ti implement custom logic.
Overall, Apache Spark's performance, flexibility, advanced analytics capabilities, and integration with the big data ecosystem make it a powerful choice for data processing, especially for scenarios that require real-time processing, machine learning, and complex analytics. However, Apache Pig still has its merits, particularly in scenarios that prioritize simplicity, ease of use, and a more procedural data processing approach.

# Question 2
A table named X contains a column of keys and a column of values:
|key|value|
|---|-----|
|k1|1|
|k1|2|
|k2|3|
|k2|4|
Note that the first row of X contains the column names. Explain how to implement the following SQL-like query in the MapReduce model:
SELECT key, SUM(value) FROM X GROUPBY key
You need to specify the key-value data in the input and output of the Map and Reduce stages.
1. Map Stage: 
- input key-value pair would be (offset, line), "offset" represents line number, "line" represents each line of the input file.
- in map function, split each line by delimiter (in this case "|")
- map function will emit intermediate key-value pairs where key is the "key" column value and the value is "value" column value. For example, ("k1",1), ("k1",2), ("k2",3), ("k2",4).
2. Reduce Stage:
- input to the reduce function would be the intermediate key-value pairs generated by map function
- in reduce, iterate over values for each key and calculate sum of values
- reduce will emite final key-value pair where the key is "key" column and value is the sum of the "value" column values. For example, ("k1", 3), ("k2", 7).
3. Input Output Specification:
- input to mapreduce job would be table x, represented as a text file
- output of mapreduce would be written to the HDFS or any designed output. 
# Question 4
```sql
--Write the HQL statements that implement the logical schema resulted in the previous step as internal tables in Hive. (You should make reasonable assumption about the row, column and file formats of the physical data.) 
CREATE EXTERNAL TABLE IF NOT EXISTS driver {
    d_license VARCHAR(255),
    first-name VARCHAR(50),
    last-name VARCHAR(50)
}
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
STORED AS TEXTFILE;

CREATE EXTERNAL TABLE IF NOT EXISTS truck {
    t_rego VARCHAR(255),
    capacity INT
}
ROW FORMAT DELIMETED FIELDS TERMINATED BY ','
STORED AS TEXTFILE;

CREATE EXTERNAL TABLE IF NOT EXISTS company {
    company_name VARCHAR(255),
    d_license VARCHAR(255),
    t_rego VARCHAR(255),
    phone INT,
    fax INT,
}
ROW FORMAT DELIMITED FIELDS TERMINATED BY ','
STORED AS TEXTFILE;
-- Write an HQL query for finding “the average number of drivers which are associated with a truck”.
SELECT AVG(driver_count) as average_drivers_per_truck
FROM (
    SELECT t.t_rego, COUNT(DISTINCT c.d_license) AS driver_count
    FROM truck 
    JOIN company c ON t.t_rego = c.t_rego
    GROUP BY t.t_rego
) subquery;
```
# Question 5
```sql
-- Write HBase shell commands that create the HBase table and load some sample data into the table. The sample data includes information about at least two accidents and two cars and one person involved in both accidents. All other information is up to you
DISABLE 'QUESTION5'
DROP 'QUESTION5'
CREATE 'QUESTION5'
DESCRIBE 'QUESTION5', 'ACCIDENT', 'DATE', 'PERSON', 'CAR', 'LOCATION'

PUT 'QUESTION5', 'DATE:01|03|23', 'DATE: day', '01'
PUT 'QUESTION5', 'DATE:01|03|23', 'DATE: month', '03'
PUT 'QUESTION5', 'DATE:01|03|23', 'DATE: year', '23'

PUT 'QUESTION5', 'PERSON:P001|John|Doe', 'PERSON: license-number', 'P001'
PUT 'QUESTION5', 'PERSON:P001|John|Doe', 'PERSON: first-name', 'John'
PUT 'QUESTION5', 'PERSON:P001|John|Doe', 'PERSON: last-name', 'Doe'

PUT 'QUESTION5', 'CAR:C001|Toyota|Camry', 'CAR: registration', 'C001'
PUT 'QUESTION5', 'CAR:C001|Toyota|Camry', 'CAR: make', 'Toyota'
PUT 'QUESTION5', 'CAR:C001|Toyota|Camry', 'CAR: model', 'Camry'
PUT 'QUESTION5', 'CAR:C002|Honda|Civic', 'CAR: registration', 'C002'
PUT 'QUESTION5', 'CAR:C002|Honda|Civic', 'CAR: make', 'Honda'
PUT 'QUESTION5', 'CAR:C002|Honda|Civic', 'CAR: model', 'Civic'

PUT 'QUESTION5', 'LOCATION: Serangoon|Singapore', 'LOCATION: street', 'Serangoon'
PUT 'QUESTION5', 'LOCATION: Serangoon|Singapore', 'LOCATION: city', 'Singapore'

PUT 'QUESTION5', 'ACCIDENT: 01|03|23|P001|John|Doe|CAR:C001|Toyota|Camry|Serangoon|Singapore|High|5000', 'DATE: day', '01'
PUT 'QUESTION5', 'ACCIDENT: 01|03|23|P001|John|Doe|CAR:C001|Toyota|Camry|Serangoon|Singapore|High|5000', 'DATE: month', '03'
PUT 'QUESTION5', 'ACCIDENT: 01|03|23|P001|John|Doe|CAR:C001|Toyota|Camry|Serangoon|Singapore|High|5000', 'DATE: year', '23'
PUT 'QUESTION5', 'ACCIDENT: 01|03|23|P001|John|Doe|CAR:C001|Toyota|Camry|Serangoon|Singapore|High|5000', 'PERSON: license-number', 'P001'
PUT 'QUESTION5', 'ACCIDENT: 01|03|23|P001|John|Doe|CAR:C001|Toyota|Camry|Serangoon|Singapore|High|5000', 'PERSON: first-name', 'John'
PUT 'QUESTION5', 'ACCIDENT: 01|03|23|P001|John|Doe|CAR:C001|Toyota|Camry|Serangoon|Singapore|High|5000', 'PERSON: last-name', 'Doe'
PUT 'QUESTION5', 'ACCIDENT: 01|03|23|P001|John|Doe|CAR:C001|Toyota|Camry|Serangoon|Singapore|High|5000',  'CAR: registration', 'C001'
PUT 'QUESTION5', 'ACCIDENT: 01|03|23|P001|John|Doe|CAR:C001|Toyota|Camry|Serangoon|Singapore|High|5000',  'CAR: make', 'Toyota'
PUT 'QUESTION5', 'ACCIDENT: 01|03|23|P001|John|Doe|CAR:C001|Toyota|Camry|Serangoon|Singapore|High|5000', 'CAR: model', 'Camry'
PUT 'QUESTION5', 'ACCIDENT: 01|03|23|P001|John|Doe|CAR:C001|Toyota|Camry|Serangoon|Singapore|High|5000', 'LOCATION: street', 'Serangoon'
PUT 'QUESTION5', 'ACCIDENT: 01|03|23|P001|John|Doe|CAR:C001|Toyota|Camry|Serangoon|Singapore|High|5000', 'LOCATION: city', 'Singapore'
PUT 'QUESTION5', 'ACCIDENT: 01|03|23|P001|John|Doe|CAR:C001|Toyota|Camry|Serangoon|Singapore|High|5000', 'ACCIDENT: severity', 'High'
PUT 'QUESTION5', 'ACCIDENT: 01|03|23|P001|John|Doe|CAR:C001|Toyota|Camry|Serangoon|Singapore|High|5000', 'ACCIDENT: damages', '5000'

-- 5.1 Find all information about the accidents having damages higher than 1000
scan 'QUESTIONS5', {FILTER => "SingleColumnValueFilter('ACCIDENT', 'damages', >, 1000)"}
-- 5.2 List first names and last names of people involved in accidents in Sydney in 2019
scan 'QUESTION5', {FILTER => "SingleColumnValueFilter('ACCIDENT', 'street', =,'Sydney') AND SingleColumnValueFilter('ACCIDENT', 'year', =, '2019')", COLUMNS => ['PERSON:first-name', 'PERSON:last-name']}
-- 5.3 Get one
get 'QUESTION5', {FILTER=> "SingleColumnValueFilter('')"}

```

# Question 6
```java
// 1)  Load the contents of a file sales.txt located in HDFS into a Resilient Distributed Dataset (RDD) and use RDD to find the total sales pert part.
val salesRDD = sc.textFile("sales.txt")
val line = salesRDD.map(s => (s.split(" ")(0)), (s.split(" ")(1).toInt))
val result = line.reduceByKey((a, b) => a+b)

// 2) Load the contents of a file sales.txt located in HDFS into a Dataset and use the Dataset to find the total sales pert part
class case Sale(name: String, quantity: int)

val salesDF = spark.sparkContext.textFile("sales.txt").map(_.split(" ").map(attributes => Sale(attributes(0), attributes(0).toInt))).toDF()
sales.toDF()

//3)
salesDF.createOrReplaceTempView("SalesView")
val salesSQL = spark.sql("SELECT part, SUM(quantity) FROM SalesView group by part")
salesSQL.show()
```