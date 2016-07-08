#!/usr/bin/env bash

# example of the run script for running the rolling_median calculation with a python file, 
# but could be replaced with similar files from any major language

# I'll execute my programs, with the input directory venmo_input and output the files in the directory venmo_output
cd src
export CLASSPATH=$PWD/:$PWD/lib/gson-2.6.2.jar
javac -cp $CLASSPATH launch/Executor.java
java -cp $CLASSPATH launch.Executor ../venmo_input/venmo-trans.txt ../venmo_output/output.txt



