#!/usr/bin/env bash
cd ./spec/
parallel -j20 'jasmine-node {}' '>' {.}.output ::: *Spec.js
