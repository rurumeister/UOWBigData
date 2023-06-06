# Question 3
The text files orders.txt and details.txt contain information about the orders submitted by 
the customers and about the details of each order, i.e. the items included in each order. Each file 
consists of a line with a header and a number of lines with data. The sample rows included in the 
files are the following. </br>
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
/bigdata/orders. Assume, that we would we would like to retrieve from the files, information 
listed below. Write HQL (Hive) statements that implement the retrievals. An important constraint is 
such that we must not replicate data already loaded to HDFS. </br>
```sql
-- 1) List the contents of the files orders.txt and details.txt.
    SELECT * FROM orders;
    SELECT * from details
-- 2) Find the total quantities summarized per order number and customer number, per customer number, and the total quantity of all orders.
-- 3) List the names of items, customer numbers and quantities of items ordered by each customer and sorted in the ascending order of the ordered quantities per each customer. Additionally, list the ranks of all items purchased by each customer.
-- 4) Find an average quantity of ordered items per item name, and per order number and customer number.
-- 5) List the names of items partitioned by item name and the progressing summations of the quantities of items ordered in the ascending order of order numbers within each partition.
```