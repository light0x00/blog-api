#!/bin/bash


if [ -z $TRAVIS_BUILD_DIR ] ;then
    base_path=/Users/light/Desktop/my-workbench/java/projects/blog-api # 没有通用的办法
else
    base_path=$TRAVIS_BUILD_DIR
fi

name=blog-api
repo=$base_path
assets=blog-web/target/blog-api.jar
to=remote/home/light/app/blog-api
to_ip=47.244.152.125
to_user=light
to_key=$base_path/id_rsa_light


$base_path/deploy/ci-shell/src/index.sh \
--mode=remote \
--skip-pull \
--skip-compile \
--app-name=$name \
--local-path=$repo \
--compile-output-path=$asstes \
--remote-ip=$to_ip \
--remote-user=$to_user \
--remote-path=$to \
--ssh-key=$to_key \
-y


if [ $? == 0 ] ; then
    ls $remote_path/$jar_name

    jar_path=$remote_path/$jar_name

    if ! [ -r $jar_path ] ;then
        echo "jar包不存在或没有权限:$jar_path"
        exit 1;
    fi
    screen -S $app_name -X quit
    screen -S $app_name java -jar $deployPath/$jar_name

fi
