# Hadoop Nodes
## Hadoop HDFS
- HDFS is distributed file system designed to run on commodity hardware.
- divides data into blocks and stores them across multiple systems.
- data is not lost, HDFS is highly fault-tolerant.
- block size is 128mb default.

### DataNode:
- manages storage node is run on, process smaller data blocks (replica nodes).
- when client wants to access DataNode, access through NameNode.

### NameNode:
- service of DataNode that translates IP address of DataNode into Metadata (Name, replicas, ...).
- master server and main server that manages the file system namespace.

## Hadoop MapReduce
### 2 Phases:
1. Mapping and splitting data on different data nodes. (Processes data by constructing key value pair from data.)
-  Key value pair will be sent to partition that have same key. (Groups into partitions)
2. Reduce Phase partitions are sent to the reducer, from each of the partition, they will sum the processes.

## MapReduce:
1. Input Splits: Data set is divided into fixed-size chunk(block) consumed by a single map.
2. Mapping: Data in each chunk is passed to mapping function to produce counts of occurences of each word, prepare a list of key-value pairs,</br>
Frequency of occurences.
3. Shuffling: Aggregate and couynt frequency.
4. Reducing: Combined into single output.

## MapReduce (without Combiner): with of without doesn't really matter, result is the same.
1. Dataset Split to chunk.
2. Map function assigns to process each block.
3. Each map produces sets of key-value pair.
4. Without combiner, sets of key-value pair are passed to Partitioner, which ensures KYP is processed.
5. With combiner, it will combine duplicate pairs.
6. Partitioner ensures it only goes into one function.
7. Shuffling sorts and groups
8. Reduce function will output set consisting of all key-value pairs.

## YARN 
- responsible for sharing resources amongst the applications running in the cluster and scheduling the task in the cluster.
- negotiates with datanodes to use etc.

### Resource Manager:
- assigns resources
### Node Manager:
- handles nodes and monitor resources usage in the node.
### Container : 
- holds a collection of physical resources.

Flow: Node Manager > Resource Manger > Client

# Starting Hadoop Server commands
```
$HADOOP_HOME/sbin/start-dfs.sh
$HADOOP_HOME/sbin/start-yarn.sh
$HADOOP_HOME/sbin/mr-jobhistory-daemon.sh start historyserver
jps
```
# Starting Zeppelin on localhost
```
cd /usr/share/zeppelin
cd bin
ls
./zeppelic-daemon.sh start
jps
```
# Stopping Hadoop Server commands
```
$HADOOP_HOME/sbin/stop-dfs.sh
$HADOOP_HOME/sbin/stop-yarn.sh
$HADOOP_HOME/sbin/mr-jobhistory-daemon.sh stop historyserver
jps
```

```
$HADOOP_HOME/bin/hadoop fs mkdir myfolder
$HADOOP_HOME/bin/hadoop fs -put $HADOOP_HOME/*.txt myfolder
$HADOOP_HOME/bin/hadoop fs -cat myfolder/*.txt
```