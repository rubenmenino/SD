#!/bin/bash

for i in $(seq -w 2 10); do
    echo Machine $i
    sshpass -p "omegalul" ssh sd302@l040101-ws$i".ua.pt" "$@"
   
done
