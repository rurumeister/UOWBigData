# Starting Hadoop
$HADOOP_HOME/sbin/start-all.sh
jps

$HADOOP_HOME/share/hadoop/mapreduce
$HADOOP_HOME/bin/hadoop jar
$HADOOP_HOME/share/hadoop/mapreduce/hadoop-mapreduce-examples-....

## Compling WordCount.java 
```apache
export HADOOP_CLASSPATH=$($HADOOP_HOME/bin/hadoop classpath)
javac -classpath ${HADOOP_CLASSPATH} WordCount.java

jar cf WordCount.jar WordCount*.class
```

## Processing wordcount.java 
```apache
$HADOOP_HOME/bin/hadoop fs -put WordCount.java
$HADOOP_HOME/bin/hadoop jar WordCount.jar WordCount WordCount.java /user/output/
// To see the result
$HADOOP_HOME/bin/hadoop fs -ls /user/output
$HADOOP_HOME/bin/hadoop fs -cat /user/ output/part-r-00000
```

## Cleaning after processing of WordCount App
```apache
$HADOOP_HOME/bin/hadoop fs -rm /user/output/*
$HADOOP_HOME/bin/hadoop fs -rmdir /user/output
//verify
$HADOOP_HOME/bin/hadoop fs -ls /user
$HADOOP_HOME/bin/hadoop fs -rm WordCount.java
```

