#!/bin/bash

if [ -z $TRAVIS_BUILD_DIR ] ;then
    app_base_path=/Users/light/Desktop/my-workbench/java/projects/blog-api # 没有通用的办法
else
    app_base_path=$TRAVIS_BUILD_DIR
fi

ci_base_path=$app_base_path/deploy/ci-shell/

# 项目名
name=blog-api
# 项目根路径
repo=$app_base_path
# 被部署目标路径
assets=$app_base_path/blog-web/target/blog-api.jar
# 部署路径
to=/home/light/app/blog-api
# 登录
to_ip=47.244.152.125
to_user=light
# 私钥路径
to_key="$app_base_path/id_rsa_light"
# 部署后运行的脚本
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


