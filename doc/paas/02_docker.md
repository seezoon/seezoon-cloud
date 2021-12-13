# 安装

[安装手册](https://docs.docker.com/engine/install/centos/)

## 安装步骤

```shell
sudo yum install -y yum-utils
sudo yum-config-manager \
    --add-repo \
    https://download.docker.com/linux/centos/docker-ce.repo
sudo yum install docker-ce docker-ce-cli containerd.io
# 上一步可能出错，如果错误提示有下面这个则执行后再次安装
# yum-config-manager --save --setopt=docker-ce-stable.skip_if_unavailable=true
```

## 命令

```shell
sudo systemctl start|stop|restart docker
```