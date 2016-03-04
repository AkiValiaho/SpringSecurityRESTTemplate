#!/usr/bin/env bash
source frontendserversettings.properties
echo $api_startingpoint
sed -i "s/${api_startingpoint}/https://localhost/g" loginApp.js

