#!/bin/bash
cat /home/light/app/.pids/blog-api.pid | xargs kill 2 > /dev/null
(java -jar /home/light/app/blog-api/blog-api.jar --MYSQL_BLOG_PWD=$MYSQL_BLOG_PWD & echo "$!" > /home/light/app/.pids/blog-api.pid)