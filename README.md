## Description

Programm will find the longest word in an input file.

## Testing environment

Programm was tested on HDP 2.4 sandbox.

## How to deploy

1. Make hadoop-1-1.0-SNAPSHOT.jar by running maven command:
```
mvn clean package
```
2. Copy hadoop-1-1.0-SNAPSHOT.jar to machine with hadoop installed.
3. Put target text file to hdfs (you can get input_example/going_postal.txt).
4. Run MapReduce job, passing hdfs path to target text file and hdfs path to output directory:
```
hadoop jar <path_to_jar>/hadoop-1-1.0-SNAPSHOT.jar <hdfs_path_to_input_file> <hdfs_path_to_output_directory>
```