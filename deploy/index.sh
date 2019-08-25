#!/bin/bash

if [ -z $TRAVIS_BUILD_DIR ] ;then
    app_base_path=/Users/light/Desktop/my-workbench/java/projects/blog-api # 没有通用的办法
else
    app_base_path=$TRAVIS_BUILD_DIR
fi

ci_base_path=$app_base_path/deploy/ci-shell/

name=blog-api
repo=$app_base_path
assets=$app_base_path/blog-web/target/blog-api.jar
to=/home/light/app/blog-api
to_ip=47.244.152.125
to_user=light
to_key='/Users/light/.ssh/id_rsa_light'
run_script=app_base_path/deploy/run.sh

$ci_base_path/src/index.sh \
--mode=remote \
--skip-pull \
--skip-compile \
--app-name=$name \
--local-path=$repo \
--compile-output-path=$assets \
--remote-ip=$to_ip \
--remote-user=$to_user \
--remote-path=$to \
--ssh-key=$to_key \
--after-deploy=$run_script \
-y


