1.编译打包
#一定要先clean
gradle clean
#然后再打包
gradle build -x test
2.生成镜像
#在本地生成带私服前缀的镜像
docker build -t 10.19.13.20:5000/yc-ucenter:v1.0 .   (每次打镜像前版本号要更新)
#将镜像推送到私服
docker push 10.19.13.20:5000/yc-ucenter:v1.0

3. 运行镜像
#--net=host  表示为主机(host)模式  去掉该配置，默认为桥接(bridge)模式
#-e 代表需要设置的环境变量
docker run -d --name yc-ucenter -p 10882:10882 \
-e "REST_REGISTRY_ADDR=10.19.13.13:29181" \
-e "REST_PORT=10882" \
-e "CONTEXT_PATH=yc-ucenter" \ 
-e "SDK_MODE=0" \
-e "PAAS_AUTH_URL=http://10.1.245.4:19811/service-portal-uac-web/service/auth" \
-e "PAAS_AUTH_PID=D14F7D708109471AB6F3084B2ABAE9A6" \
-e "PAAS_CCS_ID=CCS001" \
-e "PAAS_CCS_PWD=123456" \
-e "CCS_NAME=aiopt-aiplatform" \ 
-e "ZK_ADDR=10.19.13.13:29181"  \
10.19.13.20:5000/yc-ucenter:v1.0 
#查看镜像启动日志
docker logs yc-ucenter
#进入容器，查看镜像内部的情况
docker exec -it yc-ucenter /bin/bash
#删除运行的容器
docker rm -fv yc-ucenter

#=============更新日志========================#
*2016-09-23
1）初始版本


