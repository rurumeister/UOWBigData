# Question 4
(1) Load the contents of a file sales.txt located in HDFS into a Resilient Distributed 
Dataset (RDD) and use RDD to find the total sales per part.
```java
val salesRDD = sc.textfile("sales.txt")
val pairs = salesRDD.map(s => (s.split(" ")(0)), (s.split(" ")(1).toInt))
val result = pairs.reduceByKey((a, b) => a + b)
```
(2) Load the contents of a file sales.txt located in HDFS into a Dataset and use the 
Dataset to find the total sales per part.
```java
case class Sales(name: String, quantity: Int)
val salesLines = sc.textfile("sales.txt")
val salesDF = salesLines.map(_split(" ")).map(attributes => Sales(attributes(0), attributes(1).toInt)).toDF()
salesDF.show()
```
(3) Load the contents of a file sales.txt located in HDFS into a DataFrame and use 
SQL to find the total sales per part.
```java
//Register and select from DF
salesDF.createOrReplaceTempView("SalesView")
val sqlDF = spark.sql("SELECT NAME, SUM(quantity) from SalesView group by name")
sqlDF.show()
```