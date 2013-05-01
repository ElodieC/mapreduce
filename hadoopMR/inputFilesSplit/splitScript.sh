#!/bin/bash
for i in $( ls *.txt )
do
split -l100 -d $i $i-
done
