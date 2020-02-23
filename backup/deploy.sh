gitURL=https://github.com/light0x00/blog-api.git
gitBranch=master
appName=blog-api
sourcePath=/home/light/softwares/blog-api

set -e

# 1. get lastest code
if [[ -a $sourcePath ]];then
  cd $sourcePath
  git reset --hard $gitBranch
  git pull $gitURL $gitBranch
  git checkout $gitBranch
else
  git clone $gitURL --depth=1 -b $gitBranch $sourcePath
  cd $sourcePath
  git checkout $gitBranch
fi;

# 2. build
cd $appName
docker build . -t blog-api
docker stop blog-api
docker run

# 3. run
docker run --name blog-api \
-d \
--env TZ=Asia/Shanghai \
--link mysql-server:mysqlserver \
-p 9010:8080 \
--rm -it \
--env DB_PWD=$MYSQL_BLOG_PWD \
--env DB_USER=blog \
--env EMAIL_PWD=$EMAIL_PWD \
--env LOG_PATH=/data/blog-api/logs \
--volume /home/light/containers/blog-api:/data/blog-api \
blog-api

#docker run --name blog-api \
#--link mysql-server:mysqlserver \
#-p 9010:8080 \
#-p 9012:5005 \
#--rm -it \
#--env DB_PWD=$MYSQL_BLOG_PWD \
#--env DB_USER=blog \
#--env EMAIL_PWD=$EMAIL_PWD \
#--env LOG_PATH=/data/blog-api/logs \
#--volume /home/light/containers/blog-api:/data/blog-api \
#blog-api java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -jar blog-api.jar
