# CSCI317 - Database Performance Tuning

2019 S1 Final Examination

## Question 1: Denormalization

1. Simplification:

- Elimination of multivalued attributes
- Elimination of association classes
- Elimination of link attributes
- Elimination of many-to-many associations

2. Denormalization:
3. Decomposition:

## Question 2: Relational Algebra

Examples:

1. Find the names of parts (P_NAME) ordered by the customers in 1993 (year(O_ORDERDATE)).

```
project P_NAME(
            (projectL_PARTKEY(LINEITEM join
                            (SELECT year(O_ORDER_DATE =1994)(ORDERS))) join PART)
)
```

2. Find the names of customers (C_NAME) living in Singapore (city(C_ADDRESS))
   and the names of suppliers (S_NAME) living in Sydney (city(S_ADDRESS)) ordered
   in the ascending order of names. Use an operation sort to order the names.

```
sort C_NAME or S_NAME
(select N_NATION="Singapore" (CUSTOMER join NATION)
UNION
select N_NATION="Sydney" (CUSTOMER join NATION))
```

3. Find the keys and the names of customers(C_CUSTKEY, C_NAME) who submitted orders in 1995

```
project C_CUSTKEY, C_NAME(CUSTOMER antijoin select year(
    O_ORDERDATE = 1994
))
```

## Question 3: Syntax Tree

Find and draw a syntax tree of the query processing plan listed below.

```
-----------------------------------------------------------------------------------------
| Id | Operation               | Name     | Rows | Bytes |TempSpc| Cost (%CPU)| Time  |
-----------------------------------------------------------------------------------------
| 0 | SELECT STATEMENT         |          | 134K  | 63M  |      | 33989 (1)| 00:00:02 |
|* 1 |  HASH JOIN              |          | 134K  | 63M  | 51M  | 33989 (1)| 00:00:02 |
|* 2 |    HASH JOIN            |          | 135K  | 50M  | 1232K| 26145 (1)| 00:00:02 |
|* 3 |      HASH JOIN          |          | 4565  | 1172K|      | 2258 (1) | 00:00:01 |
|* 4 |        TABLE ACCESS FULL| PARTSUPP | 4565  | 633K |      | 1857 (1) | 00:00:01 |
|* 5 |       TABLE ACCESS FULL | PART     | 60000 | 7089K|      | 401 (1)  | 00:00:01 |
|* 6 |      TABLE ACCESS FULL  | LINEITEM | 1800K | 214M |      | 12160 (1)| 00:00:01 |
| 7  |    TABLE ACCESS FULL    | ORDERS   | 450K  | 46M  |      | 2697 (1) | 00:00:01 |
-----------------------------------------------------------------------------------------

Predicate Information (identified by operation id):
---------------------------------------------------
 1 - access("L_ORDERKEY"="O_ORDERKEY")
 2 - access("LINEITEM"."L_PARTKEY"="P_PARTKEY")
 3 - access("PART"."P_PARTKEY"="PS_PARTKEY")
 4 - filter("PARTSUPP"."PS_SUPPLYCOST"<20)
 5 - filter("PART"."P_RETAILPRICE">100)
 6 - filter("LINEITEM"."L_PARTKEY">=0)
```

ANSWER:

```
               JOIN
               /  \
              /     orders
             /
            join-----------
           /               \
          /                 \
         /                 SELECT L_PARKEY> 0
      join----------------          |
      /                   \      LINEITEM
     /                     \
select P_RETAILPRICE > 100  select P_SUPPLYCOST < 20
     |                              |
    PART                        PARTSUPP

```

## Question 4: Indexing

For each one of SELECT statements listed below find an index that speeds up the processing of a statement in the best possible way. Note, that an index must be created separately for each one of SELECT statements. Write CREATE INDEX statements to create the indexes.

```SQL
(1) SELECT P_BRAND, COUNT(_) FROM PART
GROUP BY P_BRAND
HAVING COUNT(_) > 2;

(2) SELECT AVG(L_QUANTITY) FROM ORDERS JOIN LINEITEM
ON O_ORDERKEY = L_ORDERKEY;

(3) SELECT AVG(OPS_AVAILQTY) FROM PARTSUPP
WHERE PS_SUPPNAME = 'James';

(4) SELECT P_NAME, FROM PART
WHERE P_BRAND = 'RUBBISH' ORDER BY P_NAME;

(5) SELECT C_NAME FROM CUSTOMER
WHERE C_NATIONKEY = 'SG'; MINUS
SELECT C_NAME
FROM CUSTOMER
WHERE C_ADDRESS LIKE '%Bugis%';
```

## Question 3: Indexing

## Question 4: Materialized View

## Question 5: JDBC

## Question 6: Execution Plan

1. Cardinality - refers to the estimated number of
   rows coming out of each of the operations.
2. Access Method -access path shows how
   the data will be accessed from each table or
   index.

- Full-stable scan - Reads all rows from a table and filters out those that do not meet the where clause predicates. </br>
  Is selected if: - no indexes exists, or index cannot be used or if the cost is the lowest
- Table accessed by ROWID: specifies the row in a data file, data block within that file, location of the row within that block.
  - Once rowid is obtained, Oracle locates each selected row in the table based on its rowid and does a row-by-row access.
- Index-unique scan: returns only one row that matches equality predicate on a unique B\*-tree or index.
- Index-range scan & Index-range scan descending:
  - Used when a statements has:
  1. an equality predicate or a non-unique index key
  2. Non-equality or range predicate on a unique index key
- Index-skip scan: if all other cols in the index are referenced in teh statement except the first column, Oracle can do a index skip scan. Skip the first column of the index and use the rest of it.
- Full-index scan: does not read every block in the index structure.
  - Used when all the columns necessary to satisfy the statement are in the index and it is cheaper than scanning the table.
- Fast-full-index scan
  - an ORDER BY clase, order is the same as the index.
  - merge join and all of the columns references in the query are in the index.
- Index join
- Bitmap index

3. Join Method

- Hash joins: joining large data sets, optimiser uses smaller of the, uss smaller of the two tables or data sources to build a hash table, based on the join key in memory
- Nested-Loops joins: for every row in first table (the outer table), oracle accesses all the rows in the second table (the inner table).
- Sort-merge joins: useful when join condition between two tables is an in-equality condition such as <, <=, > or >=
  - Consists of two steps:
    1.  sort join operation
    2.  merge join operation
- Cartesian join: joins every row from one data src with from one data source with every row from the other data source,creating a Cartesian product of the two sets.

4. Join Type

- Inner Join: combines output from exactly two row sources.
- Join: compares two row sources using an expression.
- Outer Join
- Full outer join
- Anti Join
- Semi Join

5. Join Order: the order in which the tables are joined together in a multi-table SQL statement.

## Question 7: Transaction Chopping
