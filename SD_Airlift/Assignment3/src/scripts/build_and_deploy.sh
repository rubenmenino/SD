#!/bin/bash


#Compile
echo "Compiling..."
$(dirname $0)/compile.sh

#Clean machines
echo "Connecting to machines..."
$(dirname $0)/connect_machines.sh "rm -rf *"
