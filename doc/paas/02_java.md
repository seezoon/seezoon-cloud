# 安装Java 基础设施

## jdk

> 安装包地址 https://jdk.java.net/archive/

```shell
cd /data
wget https://download.java.net/java/GA/jdk11/9/GPL/openjdk-11.0.2_linux-x64_bin.tar.gz
tar -zxvf openjdk-11.0.2_linux-x64_bin.tar.gz
vim /etc/profile
JAVA_HOME=/data/jdk-11.0.2
PATH=$JAVA_HOME/bin:$PATH
export JAVA_HOME PATH
#立即生效
source /etc/profile
```

## maven

> 安装包地址 https://maven.apache.org/download.cgi

```shell
cd /data
wget --no-check-certificate https://dlcdn.apache.org/maven/maven-3/3.8.4/binaries/apache-maven-3.8.4-bin.tar.gz
tar -zxvf apache-maven-3.8.4-bin.tar.gz
# 添加path
vim /etc/profile
PATH=/data/apache-maven-3.8.4/bin:$PATH
export PATH
#立即生效
source /etc/profile
```

