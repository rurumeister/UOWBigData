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
+--------------+-----------------+
| order_number | customer_number |
+--------------+-----------------+
| 0001         | 1234            |
| 0002         | 1234            |
| 0003         | 7890            |
+--------------+-----------------+
SELECT * from details;
+--------------+-------------+-----------+----------+
| order_number | line_number | item_name | quantity |
+--------------+-------------+-----------+----------+
| 0001         | 01          | bolt      | 25       |
| 0001         | 02          | screw     | 20       |
| 0002         | 01          | bolt      | 100      |
| 0002         | 02          | nut       | 300      |
| 0003         | 01          | trolley   | 1        |
+--------------+-------------+-----------+----------+
-- 2) Find the total quantities summarized per order number and customer number, per customer number, and the total quantity of all orders.
SELECT o.order_number, o.customer_number, SUM(d.quantity) as total_quantity
FROM orders o
JOIN details d ON o.order_number = d.order_number
GROUP BY o.order_number, o.customer_number WITH ROLLUP;
+--------------+-----------------+----------------+
| order_number | customer_number | total_quantity |
+--------------+-----------------+----------------+
| 0001         | 1234            | 45             |
| 0002         | 1234            | 400            |
| 0003         | 7890            | 1              |
| NULL         | NULL            | 446            |
+--------------+-----------------+----------------+
-- 3) List the names of items, customer numbers and quantities of items ordered by each customer and sorted in the ascending order of the ordered quantities per each customer. Additionally, list the ranks of all items purchased by each customer.
SELECT d.item_name, o.customer_number, d.quantity, RANK() OVER(PARTITION BY o.customer_number ORDER BY d.quantity ASC) AS item_rank
FROM orders o
JOIN details d ON o.order_number = d.order_number
ORDER BY o.customer_number, d.quantity ASC;
+-----------+-----------------+----------+-----------+
| item_name | customer_number | quantity | item_rank |
+-----------+-----------------+----------+-----------+
| screw     | 1234            | 20       | 1         |
| bolt      | 1234            | 25       | 2         |
| trolley   | 7890            | 1        | 1         |
| bolt      | 1234            | 100      | 1         |
| nut       | 1234            | 300      | 2         |
+-----------+-----------------+----------+-----------+
-- 4) Find an average quantity of ordered items per item name, and per order number and customer number.
SELECT item_name, AVG(quantity) AS avg_quantity_per_item
FROM details 
GROUP BY item_name;
+-----------+-----------------------+
| item_name | avg_quantity_per_item |
+-----------+-----------------------+
| bolt      | 62.5                  |
| screw     | 20.0                  |
| nut       | 300.0                 |
| trolley   | 1.0                   |
+-----------+-----------------------+
SELECT o.order_number, o.customer_number, AVG(d.quantity) AS avg_quantity_per_order_and_customer
FROM orders o
JOIN details d ON o.order_number = d.order_number
GROUP BY o.order_number, o.customer_number
+--------------+-----------------+-----------------------------+
| order_number | customer_number | avg_quantity_per_order_customer |
+--------------+-----------------+-----------------------------+
| 0001         | 1234            | 22.5                          |
| 0002         | 1234            | 200.0                         |
| 0003         | 7890            | 1.0                           |
+--------------+-----------------+-----------------------------+
-- 5) List the names of items partitioned by item name and the progressing summations of the quantities of items ordered in the ascending order of order numbers within each partition.
SELECT d.item_name, d.quantity, SUM(d.quantity) OVER(PARTITION BY d.item_name ORDER BY o.order_number ASC) AS cumulative_quantity
FROM orders o
JOIN details d ON o.order_number = d.order_number
GROUP BY d.item_name, o.order_number ASC;
+-----------+----------+---------------------+
| item_name | quantity | cumulative_quantity |
+-----------+----------+---------------------+
| bolt      | 25       | 25                  |
| bolt      | 100      | 125                 |
| nut       | 300      | 300                 |
| screw     | 20       | 20                  |
| trolley   | 1        | 1                   |
+-----------+----------+---------------------+
```