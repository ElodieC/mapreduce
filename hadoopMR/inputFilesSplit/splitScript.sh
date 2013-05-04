#!/bin/bash
for i in $( ls *.txt )
do
split -l100 -d -a3 $i $i-
done
