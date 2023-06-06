# Question 5
The text files orders.txt and details.txt contain information about the orders submitted by 
the customers and about the details of each order, i.e. the items included in each order. Each file 
consists of a line with a header and a number of lines with data. The sample rows included in the 
files are the following.

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
/bigdata/orders
```apache
orders = LOAD '/bigdata/orders/orders.txt' USING PigStorage(',') AS (order: chararray, number: chararray, customer-number: int)
details = LOAD '/bigdata/orders/details.txt' USING PigStorage(',') AS (order-number: chararray, line-number: int, item-name: chararray, quantity: int)
## (1) Find the customer numbers (customer-number) of the customers who ordered at least one time an item bolt and quantity of such order was larger than 100.
filtered_details = filter details BY item-name == 'bolt' AND quantity > 100;
detail_join_order = join filtered_details by order-number left outer, orders by order;
distinct_customers = DISTINCT detail_join_order.customer_number;
result = foreach distinct_customers generate customer-number;
dump result;

## (2) Find the customer numbers (customer-number) of the customers who never ordered an item bolt.
filtered_details = filter details BY item-name != 'bolt';
detail_join_order = join filtered_details by order-number left outer, orders by order;
distinct customers DISTINCT detail_join_order.customer-number;
result = foreach distinct_customers generate customer-number;
dump result;

## (3) Find the customer numbers (customer-number) of the customers who ordered an item bolt and an item screw.
bolt_orders = FILTER details BY item-name == 'bolt';
screw_orders = FILTER details BY item-name == 'screw';
distinct_bolt_customers = DISTINCT bolt_orders.customer-number;
distinct_screw_customers = DISTINCT screw_orders.customer-number;
common_customers = JOIN distinct_bolt_customers BY customer-number, distinct_screw_customers BY customer-number;
result = FOREACH common_customers GENERATE distinct_bolt_customers::customer-number;
DUMP result;

## (4) Find the customer numbers (customer-number) together with the total number of submitted orders.
grouped_customer = group orders by customer-number;
result = foreach grouped_customer generate group, COUNT(orders);
dump result;
```