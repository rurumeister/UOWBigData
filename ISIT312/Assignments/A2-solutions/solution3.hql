CREATE TABLE employees (
employeeId VARCHAR(3),
fullName  VARCHAR(60),
projects MAP<STRING, INT>,
languages ARRAY<STRING>)
ROW FORMAT DELIMITED FIELDS TERMINATED BY '|'
STORED AS TEXTFILE;

INSERT INTO employees
SELECT '007', 'James Bond', map('DB/3', 30, 'Oracle', 25, 'SQL-2022', 100), array('Java','C','C++');
INSERT INTO employees
SELECT '008', 'James Bond', map('DB/3', 70, 'Oracle', 100), array();
INSERT INTO employees
SELECT '010', 'Robin Banks', map(null, cast(null as int)), array('C','Rust');
INSERT INTO employees
SELECT '009', 'Robin Hood', map(null, cast(null as int)), array();
INSERT INTO employees
SELECT '011', 'Elroy Chua', map('CinemaBookingSytem', 100), array('React', 'NextJS', 'Postgresql');

SELECT * FROM employees;


