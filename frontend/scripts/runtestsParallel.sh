#!/usr/bin/env bash
cd ./spec/
parallel 'jasmine-node {}' '>' {.}.output ::: *Spec.js
