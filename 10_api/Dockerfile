FROM openjdk:8
MAINTAINER icql

ADD icql-api.jar /opt/app/icql-api.jar
RUN bash -c 'touch /opt/app/icql-api.jar'

# 开放8513端口
EXPOSE 8513

ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 配置容器启动后执行的命令
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/opt/app/icql-api.jar","--spring.profiles.active=prod","--jasypt.encryptor.password=icql"]