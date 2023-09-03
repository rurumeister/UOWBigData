# ML with Apache Spark

#### Large-Scale ML

- MR and Spark is powerful for distributed computation.

```python
from pyspark.sql import SparkSession
spark = SparkSession.builder.appName('CSCI316-Lab6').config('spark-master', 'local').getOrCreate()
# output the object (spark) information
directory = 'C://Users//japid//Documents//CSCI316'
df_RD = spark.read.format('csv').option('header', 'true').option('inferSchema', 'true')
.option('inferSchema', 'true').load(directory+'/2010-12-01.csv')
#print the schema
df.printSchema()

# Count the unique values in the 'Invoice' column
df_RD.show(2)
df_RD.select('InvoiceNo').distinct().count()
# Define a new DF that inclues on the 'StockCode' and 'Quantity' columns of df_RD.
from pyspark.sql.functions import struct, col
from pyspark.sql.types import *
#create a new DF using struct and col

```

## PySpark and Spark MLlib

Pandas and Spark DataFrame are designed for structural and semistructral data processing.

- Operations
