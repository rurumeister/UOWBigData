# Task 4
Implementation of a data warehouse as a collection of external tables in Hive

Implement HQL script solution4.hql that creates the external tables obtained from 
a step of logical design performed earlier. The external tables must overlap on the files 
transferred to HDFS in the previous step. Note, that a header in each *.tbl file must be
removed before creating the external tables

```sql
DROP TABLE part;
CREATE TABLE IF NOT EXISTS part (
    p_partkey INT,
    p_name VARCHAR(255),
    p_mfgr VARCHAR(50),
    p_brand VARCHAR(50),
    p_type VARCHAR(50),
    p_size INT,
    p_container VARCHAR(50),
    p_retailprice DECIMAL(10,2),
    p_comment VARCHAR(255)
)
ROW FORMAT DELIMITED FIELDS TERMINATE BY '|'
stored as textfile
LOCATION '/user/hive/warehouse/a2task4.db/part';

DROP TABLE supplier;
CREATE EXTERNAL TABLE IF NOT EXISTS supplier (
    s_suppkey INT,
    s_name VARCHAR(255),
    s_unknown INT,
    s_address VARCHAR(255),
    s_phone VARCHAR(50),
    s_acctbal DECIMAL(10,2),
    s_comment VARCHAR(255)
)
ROW FORMAT DELIMITED FIELDS TERMINATED BY '|'
stored as textfile
LOCATION '/user/hive/warehouse/a2task4.db/supplier';

DROP TABLE partsupp;
CREATE EXTERNAL TABLE IF NOT EXISTS partsupp (
    p_partkey INT,
    s_suppkey INT,
    ps_availqty INT,
    ps_supplycost DECIMAL(10,2),
    ps_comment VARCHAR(255)
)
ROW FORMAT DELIMITED FIELDS TERMINATED BY '|'
stored as textfile
LOCATION '/user/hive/warehouse/a2task4.db/partsupp';

-- Query first 5 contents of external tables
SELECT * FROM part LIMIT 5;
SELECT * FROM supplier LIMIT 5;
SELECT * FROM partsupp LIMIT 5;

-- Count the number of rows in each table 
SELECT COUNT(*) FROM part;
SELECT COUNT(*) FROM supplier;
SELECT COUNT(*) FROM partsupp;
```