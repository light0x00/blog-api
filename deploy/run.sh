# 此脚本的STDUT将会作为部署后,运行应用的脚本.
echo "screen -S $app_name -X quit ;"
echo "screen -dmS $app_name java -jar $remote_path/blog-api.jar --MYSQL_BLOG_PWD=\$MYSQL_BLOG_PWD"
