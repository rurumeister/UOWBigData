# Question 3
```sql
CREATE TABLE ORDERS(
ORDERKEY VARCHAR(20), /* Orders dimension */
PARTKEY VARCHAR(12), /* Parts dimension */
SUPPKEY VARCHAR(12), /* Suppliers dimension */
QUANTITY DECIMAL(7), /* Quantity measure */
DISCOUNT DECIMAL(4,1) ); /* Discount measure */
```
```sql
-- (1) For each part list its key (PARTKEY), all its quantities (QUANTITY) and the largest quantity. 
SELECT PARTKEY, QUANTIY, MAX(quantity) OVER (PARTITION BY PARTKEY) as largest_quantity
FROM ORDERS;

-- (2) Compute an average applied discount (DISCOUNT) for the parts with the keys (PARTKEY) 1001 and 1002 and list the part keys and the average applied discount.
SELECT PARTKEY, AVG(DISCOUNT) OVER (PARTITION BY PARTKEY) AS AVG_APPLIED
FROM ORDERS;

-- (3) For each part list its key (PARTKEY), all its applied discounts, and an average applied discount (DISCOUNT) of the current discount and the previous one in the ascending order of available discounts.
SELECT PARTKEY, DISCOUNT, AVG(DISCOUNT) OVER (PARTITION BY PARTKEY ORDER BY DISCOUNT ASC ROWS BETWEEN 1 PRECEDING AND CURRENT ROW ) AS average_discount
FROM ORDERS
ORDER BY DISCOUNT ASC;

-- (4) For each part list its key (PARTKEY), all its applied discounts, and an average applied discount (DISCOUNT) of the current quantity and all previous discounts in the ascending order of applied discounts
SELECT PARTKEY, DISCOUNT, AVG(DISCOUNT) OVER (PARTITION BY PARKEY ORDER BY DISCOUNT ASC ROWS BETWEEN UNBOUND PRECEDING AND CURRENT ROW) AS average_discount
FROM ORDERS
ORDER BY DISCOUNT ASC;
```

# Question 4
```apache
## 1)Use HBase shell command language to write the commands that create HBase table implementing a two-dimensional data cube given above
DISABLE 'SOLUTION1'
DROP 'SOLUTION1'
CREATE 'SOLUTION1', 'PRODUCT', 'ORDER', 'CUSTOMER'
DESCRIBE 'SOLUTION1'

## 2)  Write the commands of HBase shell command language that insert into HBase table created in the previous step information about at least 2 orders submitted by the same customer and including two different products.
PUT 'SOLUTION1', 'PRODUCT: P01|MAC','PRODUCT: code', 'P01'
PUT 'SOLUTION1', 'PRODUCT: P01|MAC','PRODUCT: name', 'MAC'
PUT 'SOLUTION1', 'PRODUCT: P02|IPHONE','PRODUCT: code', 'P02'
PUT 'SOLUTION1', 'PRODUCT: P02|IPHONE','PRODUCT: name', 'IPHONE'
PUT 'SOLUTION1', 'CUSTOMER: 12345678|John|Doe', 'CUSTOMER: phone', '12345678'
PUT 'SOLUTION1', 'CUSTOMER: 12345678|John|Doe', 'CUSTOMER: first-name', 'John'
PUT 'SOLUTION1', 'CUSTOMER: 12345678|John|Doe', 'CUSTOMER: last-name', 'Doe'
PUT 'SOLUTION1', 'CUSTOMER: 12345679|Jenna|Lee', 'CUSTOMER: phone', '12345679'
PUT 'SOLUTION1', 'CUSTOMER: 12345679|Jenna|Lee', 'CUSTOMER: first-name', 'Jenna'
PUT 'SOLUTION1', 'CUSTOMER: 12345679|Jenna|DLee', 'CUSTOMER: last-name', 'Lee'
PUT 'SOLUTION1', 'ORDER: O001|5|5000|0.5|P02|12345678', 'ORDER: order-number', 'O001'
PUT 'SOLUTION1', 'ORDER: O001|5|5000|0.5|P02|12345678', 'ORDER: quantity', '5'
PUT 'SOLUTION1', 'ORDER: O001|5|5000|0.5|P02|12345678', 'ORDER: amount-paid','5000'
PUT 'SOLUTION1', 'ORDER: O001|5|5000|0.5|P02|12345678', 'ORDER: discount', '0.5'
PUT 'SOLUTION1', 'ORDER: O001|5|5000|0.5|P02|12345678', 'PRODUCT: code', 'P02'
PUT 'SOLUTION1', 'ORDER: O001|5|5000|0.5|P02|12345678', 'CUSTOMER: phone', 12345678

##  Write the commands of HBase shell command language that retrieve from HBase table created in the previous step the following information:
##- list all column families for an order number 123,
scan 'SOLUTION2', {FILTER => "KeyOnlyFilter() AND SingleColumnValueFilter('ORDER', 'order-number', =, '123')"}

##- list no more than 2 versions of product name used in an order 123,
scan 'SOLUTION2', {FILTER => "SingleColumnValueFilter('ORDER', 'order-number', =, '123')", COLUMNS => 'PRODUCT: name', VERSIONS => 2}

##- list at most 2 versions of orders with timestamps in a range from 1 to 5,
scan 'SOLUTION', {FILTER => "TimestampsFilter(1, 5)", VERSIONS => 2}

##- list all codes of products ordered by a customer whose phone is 345.
scan 'SOLUTION2', {FILTER => "SingleColumnValueFilter('CUSTOMER', 'phone', =, '345')", COLUMNS => 'PRODUCT:code'}
```

# Question 5
```apache
## Assume that '|' (vertical bar has been used to separate data items in each row in the data files customer.txt, product.txt, and order.txt. 
## Assume that the data files customer.txt, product.txt, and order.txt has been uploaded into HDFS. 
product = load 'customer.txt' using PigStorage('|')
as
(code: chararray, name: chararray);

order = load 'order.txt' using PigStorage('|')
as
(order-number: chararray, quantity: int, amount-paid: int, discount: float, code: chararray, phone: chararray);

customer = load 'customer.txt' using PigStorage('|')
as
(phone: chararray, first-name: chararray, last-name: chararray);
## Write a sequence of Pig-Latin commands that find the total quantities of products grouped by product name
joined_data = join ORDER by code, PRODUCT by code;
grouped_data = GROUP joined_data BY product::name;
result = foreach grouped_data generate group as product_group, SUM(joined_data.quantity) as product_quantity
dump result;
```

# Question 6
```apache
## (1) Create a DataFrame named orderDetailsDF that contains information about the details of orders included in a file order-details.tbl.
val orderDetailsDF = spark.load('order-details.tbl')
## 2) List all order details where discount is less than 0.5.
.createOrReplaceTempView("OrderDetails")
val orderDetails = spark.sql("SELECT * FROM OrderDetails WHERE discount < 0.5")
## Find the total number of customers located in Germany
```