## Solution for Assignment 3, Task 3
## Student number: 7431673
## Student name: Elroy Chua Ming Xuan

## Loading up Tables
customer = load 'customer.tbl' using PigStorage('|')
as
(CUSTOMER_ID:int, CUSTOMER_CODE:chararray, COMPANY_NAME:chararray, CONTACT_NAME:chararray, CONTACT_TITLE:chararray, CITY:chararray, REGION: chararray, POSTAL_CODE: chararray, COUNTRY: chararray, PHONE:chararray, FAX:chararray);
salesPerson = load 'salesperson.tbl' using PigStorage('|')
as 
(EMPLOYEE_ID:int, LASTNAME:chararray, FIRSTNAME:chararray, TITLE:chararray, BIRTHDATE:chararray, HIREDATE:chararray, NOTES:chararray);
orderDetail = load 'order_detail.tbl' using PigStorage('|')
as
(ORDER_ID:int, PRODUCT_ID:int, CUSTOMER_ID:int, SALESPERSON_ID:int, UNIT_PRICE:float, QUANTITY:int, DISCOUNT:float);
product = load 'product.tbl' using PigStorage ('|')
as
(PRODUCT_ID:int, PRODUCT_NAME:chararray, UNIT_PRICE:float, UNITS_IN_STOCK:int, UNITS_ON_ORDER:int, DISCONTINUED:int);
orders = load 'order.tbl' using PigStorage('|')
as
(ORDER_ID:int, ORDER_DATE:chararray, SHIP_VIA:int, SHIP_CITY:chararray, SHIP_REGION:chararray, SHIP_POSTAL_CODE:chararray, SHIP_COUNTRY:chararray);

## 2) Find the first and the last name (first-name, last-name) of sales people who handled the orders submitted by a company Consolidated Holdings
targetCompany = filter customer by COMPANY_NAME == ' Consolidated Holdings ';
targetCompanyJoinOrderDetail = JOIN targetCompany BY CUSTOMER_ID, orderDetail BY CUSTOMER_ID;
salesPersonJoinCustomerOrderDetail = JOIN targetCompanyJoinOrderDetail by SALESPERSON_ID, salesPerson by EMPLOYEE_ID;
targetSalesPersonResult = foreach salesPersonJoinCustomerOrderDetail generate FIRSTNAME, LASTNAME;
result2 = distinct targetSalesPersonResult;
dump result2;

## 3)Find the total number of products not ordered in 1996, groupBy, filter out from order, take out from 1996, group by and count.
order_join_orderDetail = join orderDetail by ORDER_ID left outer, orders by ORDER_ID;
order1996 = foreach(filter order_join_orderDetail by ORDER_DATE matches '.*1996.*') generate orderDetail::ORDER_ID, ORDER_DATE;
order1996_join_orderDetail = join orderDetail by ORDER_ID left outer, order1996 by ORDER_ID;
orderNot1996 = foreach(filter order1996_join_orderDetail by orders::ORDER_DATE is null) generate orderDetail::ORDER_ID, orders::ORDER_DATE;
groupOrderNot1996 = group orderNot1996 by ORDER_ID;
countOrderNot1996 = foreach groupOrderNot1996 generate COUNT(orderNot1996);
totalNot1996 = group countOrderNot1996 ALL;
result3 = foreach totalNot1996 generate SUM(countOrderNot1996);
dump result3;

## 4) Summarizations of quantities (quantity) per ordered product (product-id), Use group __ by __ and SUM the quantity you want to find
productJoinOrderDetail = JOIN orderDetail by PRODUCT_ID left outer, product by PRODUCT_ID;
orderQty = foreach(filter productJoinOrderDetail by orderDetail::PRODUCT_ID is not null) generate orderDetail::PRODUCT_ID, QUANTITY;
orderGrp = group orderQty by PRODUCT_ID;
result4 = foreach orderGrp generate group, COUNT(orderQty);
dump result4;

## 5) Find the identifiers of orders (order-id) that included both Ikura and Tofu. Use intersection, container called Ikura, contains all Ikura, another container that is Tofu. In this segment, where Tofu and Ikura exists.
product_join_orderDetail = join orderDetail by PRODUCT_ID, product by PRODUCT_ID;
ikura = foreach(filter product_join_orderDetail by PRODUCT_NAME == ' Ikura ') generate ORDER_ID, PRODUCT_NAME;
tofu = foreach (filter product_join_orderDetail by PRODUCT_NAME == ' Tofu ') generate ORDER_ID, PRODUCT_NAME;
ikuraAndtofu = join ikura by ORDER_ID left outer, tofu by ORDER_ID;
totalOrder = foreach(filter ikuraAndtofu by tofu::product::PRODUCT_NAME is not null) generate ikura::orderDetail::ORDER_ID;
distTotal = distinct totalOrder;
dump distTotal;




