# Question 3
In this question we use the same file hightemp.txt as in Question 1 of the examination paper. 
A file hightemp.txt contains information about the highest temperatures recorded every day in 
a number of cities all over the world. The file higtemp.txt is a text file where information about 
the highest temperature recorded on a given day, in a given city is stored in a single row. Data
items like date, temperature and city name are separated with a single blank. 
A file hightemp.txt has been loaded to HDFS to a location /bigdata/hightemp. We must 
not replicate data already loaded to HDFS.

```sql
-- 1)  Write HQL statements that prepare a file hightemp.txt to be accessed through SELECT statements. Next, write SELECT statement that lists the total number of cities where the temperature measurements were recorded.
CREATE EXTERNAL TABLE IF NOT EXISTS hightemp (
    date STRING,
    temperature INT,
    city STRING
)
ROW FORMAT DELIMITED FIELDS TERMINATED BY ' '
LOCATION '/bigdata/hightemp';
-- Retrieve the total number of cities with temperature measurements
SELECT COUNT(DISTINCT city) AS total_cities
FROM hightemp;

-- 2) Write SELECT statement that lists the names of cities, temperature measurements and dates sorted in the ascending order of the temperature measurements per each city. Additionally, list a rank of each temperature measurement in each city.
SELECT city, temperature, date,RANK() OVER(PARTITION BY city ORDER BY temperature ASC) AS RANK
FROM hightemp
ORDER BY temperature ASC;

-- 3) Write SELECT statement that lists an average temperature per year and per year and city.
-- Average temperature per year 
SELECT SUBSTRING(date, -4) as year, AVG(temperature) as avg_temp
FROM hightemp
GROUP BY SUBSTRING(date, -4)
ORDER BY year;
-- Average temperature per year and city
SELECT SUBSTRING(date, -4) as year, AVG(temperature) as avg_temp
FROM hightemp
GROUP BY SUBSTRING(date, -4), city
ORDER BY year, city;
-- 4) Write SELECT statement that lists an average temperature per year and city, per city, and an average temperature from all measurements.
-- avg temp per year per city
SELECT SUBSTRING(date, -4) as year, AVG(temperature) as avg_temp, city
FROM hightemp
GROUP BY city
ORDER BY year;
--avg temp per city
SELECT city, AVG(temperature) as avg_temp
FROM hightemp
GROUP BY city
ORDER BY city;
--average temperature from all measurements
SELECT AVG(temperature) as avg_temperature
FROM hightemp;
-- 5) Write SELECT statement that lists five month moving average of temperate measurements per each city.
SELECT city, date, temperature, AVG(temp) OVER (PARTITION BY city ORDER BY date ROWS BETWEEN 4 PRECEDING AND CURRENT ROW) as five_month_avg
FROM hightemp
ORDER BY city, date;
```