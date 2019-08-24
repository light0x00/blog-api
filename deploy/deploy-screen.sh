#!/bin/bash
echo "after deploy!!!!"

jar_name='blog-api.jar'
ls $deployPath/$jar_name

#screen -S $appName -X quit
#screen -S $appName java -jar $deployPath/$jar_name