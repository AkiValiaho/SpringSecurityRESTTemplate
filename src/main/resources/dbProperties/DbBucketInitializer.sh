#!/bin/bash
sudo apt-get update
sudo apt-get -y install curl
#Fill in the blanks please
curl -X POST -u admin:bucketpassword -d name=bucketname -d ramQuotaMB=200 -d authType=none -d replicaNumber=2 http://127.0.0.1:8091/pools/default/buckets
