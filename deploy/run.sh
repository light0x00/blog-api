#!/bin/bash
screen -S blog-api -X quit 2> /dev/null;
screen -dmS blog-api java -jar /home/light/app/blog-api/blog-api.jar --MYSQL_BLOG_PWD=$MYSQL_BLOG_PWD