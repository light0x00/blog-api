#!/bin/bash

if [ -z $TRAVIS_BUILD_DIR ] ;then
    app_base_path=/Users/light/Desktop/my-workbench/java/projects/blog-api # 没有通用的办法
else
    app_base_path=$TRAVIS_BUILD_DIR
fi

# 项目名
name=blog-api
# 项目根路径
repo=$app_base_path
# 被部署资源路径
assets_path=$app_base_path/blog-web/target/blog-api.jar
# 部署路径
remote_path=/home/light/app/blog-api
# 登录
remote_ip=47.244.152.125
remote_user=light
# 私钥路径
ssh_key="$app_base_path/id_rsa_light"
# 部署后运行的脚本
run_script="$app_base_path/deploy/run.sh"


# ------------------------------------------------------------

source $app_base_path/deploy/ci-shell/src/libs/index.sh

open_ssh_agent

deploy_to_remote

exec_script_remote $run_script

close_ssh_agent




