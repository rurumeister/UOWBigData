# Task 3
Assume that we have a collection of semi-structured data with information about the 
employees (unique employee number and full name) the projects they are assigned to 
(project name and percentage of involvement) and their programming skills (the names of 
known programming languages). Some of the employees are on leave and they are not 
involved in any project. Also, some of the employees do not know any programming 
languages. Few sample records from the collection are listed below.

```
007|James Bond|DB/3:30,Oracle:25,SQL-2022:100|Java,C,C++
008,Harry Potter|DB/3:70,Oracle:75| 
010,Robin Banks| |C,Rust
009,Robin Hood| |
```

(1) Implement HQL script solution3.hql that creates an internal relational table to 
store information about the employees, the projects they are assigned to (project 
name and percentage of involvement) and their programming skills. \
(2) Include into the script INSERT statements that load sample data into the table.
Insert at least 5 rows into the relational table created in the previous step. Two 
employees must participate in few projects and must know few programming 
languages. One employee must participate in few projects and must not know any 
programming languages. One employee must know few programming languages and 
must not participate in any projects. One employee must not know programming 
languages and must not participate in the projects. \
(3) Include into the script SELECT statements that list the contents of the table.

```sql
CREATE TABLE EMPLOYEES(
    employeeId VARCHAR(3),
    fullName VARCHAR(60),
    projects MAP<STRING, INT>,
    languages ARRAY<STRING>)
ROW FORMAT DELIMITED FIELDS TERMINATED BY '|'
STORED AS TEXTFILE;

INSERT INTO EMPLOYEES
SELECT '007', 'James Bond', map('DB/3', 30, 'Oracle', 25, 'SQL-2022', 100), array('Java', 'C', 'C++');
SELECT '008', 'Harry Pooter', map('DB/3', 70, 'Oracle', 75), array();
SELECT '010', 'Robin Banks', map(null, cast(null as int)), array('C','Rust');
SELECT '009', 'Robin Hood', map(null, cast(null as int)), array();
SELECT '011', 'Elroy Chua', map('CinemaBookingSytem', 100), array('React', 'NextJS', 'Postgresql');

SELECT * FROM employees;

```