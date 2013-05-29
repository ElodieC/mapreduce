#!/bin/bash

#Local variable to change
hadoopPath=/usr/local/hadoop
inputPath=/home/hduser/workspace/mapreduce/hadoopMR
dfsInputPath=/home/hduser/hadoop
dfsOutputPath=/home/hduser/hadoop/output
splitScriptPath=$inputPath/splitScript.sh

#Please start the Hadoop server before launching this script

#Copy files to Hadoop DFS
$hadoopPath/bin/hadoop dfs -rmr $dfsInputPath/inputFiles
$hadoopPath/bin/hadoop dfs -copyFromLocal $inputPath/inputFiles $dfsInputPath

$hadoopPath/bin/hadoop dfs -rmr $dfsOutputPath

#Execute MapReduce task
$hadoopPath/bin/hadoop jar $inputPath/hadoopIndex.jar IndexDriver $dfsInputPath/inputFiles $dfsOutputPath 

#Copy the result on the Linux FS
rm -r $inputPath/outputFiles
mkdir $inputPath/outputFiles
$hadoopPath/bin/hadoop dfs -getmerge $dfsOutputPath $inputPath/outputFiles
mv $inputPath/outputFiles/output  $inputPath/outputFiles/output.txt

#Split files for the search engine
rm -r $inputPath/inputFilesSplit
mkdir $inputPath/inputFilesSplit
cp $splitScriptPath $inputPath/inputFilesSplit/
cp $inputPath/inputFiles/* $inputPath/inputFilesSplit/
cd $inputPath/inputFilesSplit/
$inputPath/inputFilesSplit/splitScript.sh

