FROM hub.docker.com/centos/centos:6.10

MAINTAINER 2019-04-03 sunny sunlin901203@qq.com

# 设置时区
RUN cp -f /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone

# hosts
COPY ./starfish-notify-server/kubernetes/hosts /tmp/
RUN cat /tmp/hosts >> /etc/hosts

# 创建目录
RUN mkdir -p /opt/workspace/

# 安装软件
RUN yum install wget -y
RUN yum install gcc -y
RUN yum install net-tools -y
RUN yum install telnet.x86_64 -y
RUN yum install xinetd.x86_64 -y

# java
WORKDIR /opt
RUN curl -L -O -H "Cookie: oraclelicense=accept-securebackup-cookie" -k "http://download.oracle.com/otn-pub/java/jdk/8u191-b12/2787e4a523244c269598db4e85c51e0c/jdk-8u191-linux-x64.tar.gz"
RUN tar zxvf jdk-8u191-linux-x64.tar.gz && rm -f jdk-8u191-linux-x64.tar.gz && ln -s jdk1.8.0_191 java
ENV JAVA_HOME=/opt/java
ENV PATH=${PATH}:/opt/java/bin
RUN echo "JAVA_HOME=$JAVA_HOME" && echo "PATH=$PATH" &&  ls -l /opt

# arthas
WORKDIR /opt/arthas/
RUN curl -O https://alibaba.github.io/arthas/arthas-boot.jar

# 其他
# 重命名ls -l
RUN alias ll="ls -al"

# 项目设置
ENV NAME starfish-notify
WORKDIR /opt/workspace/starfish-notify
ADD ./starfish-notify-server/target/starfish-notify-server-1.0.0.jar /opt/workspace/starfish-notify

# 处理jar包中的资源
RUN jar -xvf /opt/workspace/starfish-notify/starfish-notify-server-1.0.0.jar
RUN cp /opt/workspace/starfish-notify/BOOT-INF/classes/bash/tail.sh /opt/workspace/starfish-notify/
RUN chmod 755 /opt/workspace/starfish-notify/tail.sh

# 启动
CMD java -jar -Xms2G -Xmx4G -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -XX:+UseConcMarkSweepGC -XX:+UseCMSCompactAtFullCollection  -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/opt/logs/starfish-notify/ /opt/workspace/starfish-notify/starfish-notify-server-1.0.0.jar;