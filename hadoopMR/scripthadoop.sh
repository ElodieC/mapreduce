#!/bin/bash

#Local variable to change
hadoopPath=/usr/local/hadoop
inputPath=/home/hduser/hadoopMR
dfsInputPath=/home/hduser/hadoop
dfsOutputPath=/home/hduser/hadoop/output

#Start Hadoop Server
$hadoopPath/bin/start-all.sh
$hadoopPath/bin/hadoop dfsadmin -safemode leave
#Copy files to Hadoop DFS
$hadoopPath/bin/hadoop dfs -rmr $dfsInputPath/inputFiles
$hadoopPath/bin/hadoop dfs -copyFromLocal $inputPath/inputFiles $dfsInputPath

$hadoopPath/bin/hadoop dfs -rmr $dfsOutputPath

#Execute MapReduce task
$hadoopPath/bin/hadoop jar $inputPath/hadoopIndex.jar IndexDriver

#Copy the result on the Linux FS
rm $inputPath/outputFiles/*
$hadoopPath/bin/hadoop dfs -getmerge $dfsOutputPath $inputPath/outputFiles
mv $inputPath/outputFiles/output  $inputPath/outputFiles/output.txt

#Stop Hadoop Server
$hadoopPath/bin/stop-all.sh