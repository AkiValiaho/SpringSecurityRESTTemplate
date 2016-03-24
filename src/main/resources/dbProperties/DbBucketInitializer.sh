#!/bin/bash

sudo apt-get update
sudo apt-get -y install curl
#Read file
prefix="db."
echo $prefix
#Initialize variables from .properties-file
while read -r line;
do declare ${line#$prefix};
echo ${line#$prefix};
done <db.properties

curl -X POST -u admin:"$bucketPassword"-d name="$bucket" -d ramQuotaMB=200 -d authType=none -d replicaNumber=2 http://127.0.0.1:8091/pools/default/buckets
