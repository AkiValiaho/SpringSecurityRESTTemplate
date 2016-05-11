#!/bin/bash

sudo apt-get update
sudo apt-get -y install curl
#Read file
prefix="db."
echo $prefix
#Initialize variables from .properties-file
while read -r line;
do declare ${line#$prefix};
	echo ${line}
	# #-operator removes SHORTEST instance of $prefix
	# from $line
	echo "Using variable format ${line#$prefix}"
done <db.properties
echo ""
echo "-----------------------------------------------------"
echo "-----------------------------------------------------"
echo "db.properties was used for initializing the variables"
echo "-----------------------------------------------------"
echo "-----------------------------------------------------"
echo ""
echo "Initializing new bucket with password: ${bucketPassword} and bucket name: ${bucket}"
echo ""
curl -X POST -u admin:"$bucketPassword"-d name="$bucket" -d ramQuotaMB=200 -d authType=none -d replicaNumber=2 http://127.0.0.1:8091/pools/default/buckets
