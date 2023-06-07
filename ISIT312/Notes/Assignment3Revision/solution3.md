# Question 3
```apache
#(1) Remove the headers and transfer the files into HDFS. Create Pig Latin script solution3.pig that implements the following queries.

#(2) Find the first and the last name (first-name, last-name) of sales people who handled the orders submitted by a company Consolidated Holdings.
filtered_company = filter customer by company-name == "Consolidated Holdings";
company_join_order = join filtered_company by customer-id, order by order-id;
left 
left 
join
return

#(3) Find the total number of products not ordered in 1996.
#(4) Find the summarizations of quantities (quantity) per ordered product (product-id).
#(5) Find the identifiers of orders (order-id) that included both Ikura and Tofu

```
