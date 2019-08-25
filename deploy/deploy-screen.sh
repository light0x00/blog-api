#!/bin/bash

jar_name='blog-api.jar'
ls $remote_path/$jar_name

jar_path=$remote_path/$jar_name

if ! [ -r $jar_path ] ;then
    echo "jar包不存在或没有权限:$jar_path"
    exit 1;
fi

screen -S $app_name -X quit
screen -S $app_name java -jar $deployPath/$jar_name