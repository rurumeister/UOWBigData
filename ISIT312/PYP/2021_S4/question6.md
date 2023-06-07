# Question 6
The text files orders.txt and details.txt contain information about the orders submitted by the customers and about the details of each order, i.e. the items included in each order. Each file consists of a line with a header and a number of lines with data. The sample rows included in the files are the following.

```
orders.txt
order,number,customer-number
0001,1234
0002,1234
0003,7890
... ...
details.txt
order-number,line-number,item-name,quantity
0001,01,bolt,25
0001,02,screw,20
0002,01,bolt,100
0002,02,nut,300
0003,01,trolley,1
... ...
```

The headers of the files have been removed and the files have been loaded to HDFS to a location 
/bigdata/orders.

```scala
// (1) Load the contents of a file details.txt located in HDFS into a Resilient Distributed Dataset (RDD) and use RDD to find the total quantity per each item.
val detailsRDD = sc.textFile("details.txt")
val itemQuantityRDD = detailsRDD
  .map(line => {
    val columns = line.split(",")
    val itemName = columns(2)
    val quantity = columns(3).toInt
    (itemName, quantity)
  })
  .reduceByKey(_ + _)
val itemQuantityList = itemQuantityRDD.collect()

// (2) Load the contents of a file details.txt located in HDFS into a Dataset and use the Dataset to find the total quantity per each item.
case class Detail(orderNumber: String, lineNumber: String, itemName: String, quantity: Int)
// Read the details.txt file into a Dataset
val lines = sc.textFile("details.txt")
val detailsDF = lines.map(_.split(",")).map(attributes => Detail(attributes(0), attributes(1), attributes(2), attributes(3).toInt))
val totalResult = detailsDS
  .groupBy("itemName")
  .agg(functions.sum("quantity").alias("totalQuantity"))
// Show the result
totalResult.show()

// (3) Load the contents of a file details.txt located in HDFS into a DataFrame and use SQL to find the total quantity per each item.
detailsDF.createOrReplaceTempView("DetailsView")
val totalQuantityPerItem = spark.sql("""
  SELECT item_name, SUM(quantity) AS total_quantity
  FROM details
  GROUP BY item_name
""")

```