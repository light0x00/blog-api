#!/bin/bash

base_path=$TRAVIS_BUILD_DIR

$base_path/deploy/ci-shell/src/index.sh \
--mode=remote \
--skip-pull \
--skip-compile \
--app-name=blog \
--local-path=$base_path \
--compile-output-path=blog-web/target/ \
--remote-ip=47.244.152.125 \
--remote-user=light \
--remote-path=/home/light/app/blog-api \
--ssh-key=$base_path/id_rsa_light \
-y \
--after-deploy=$base_path/deploy/deploy-screen.sh



