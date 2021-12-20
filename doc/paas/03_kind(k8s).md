# 安装

kind 是已docker 容器模拟的node
[安装手册](https://kind.sigs.k8s.io/docs/user/quick-start/)

## 安装步骤

```shell
curl -Lo ./kind https://kind.sigs.k8s.io/dl/v0.11.1/kind-linux-amd64
chmod +x ./kind
mv kind /usr/local/bin
```

## 创建集群

```shell
# 创建默认集群
kind create cluster [--name=xxx]
# 指定k8s版本  k8s镜像版本 https://hub.docker.com/r/kindest/node/tags
kind create cluster --image=xxx
```

推荐下面的方式

```yaml
# kind-config.yaml
kind: Cluster
apiVersion: kind.x-k8s.io/v1alpha4
nodes:
  - role: control-plane
  - role: worker
  - role: worker
  - role: worker
  - role: worker
  - role: worker
  - role: worker
  - role: worker
  - role: worker
  - role: worker
  - role: worker
```

```shell
# 创建多个worker节点 比较接近真实环境  https://kind.sigs.k8s.io/docs/user/quick-start/#configuring-your-kind-cluster
kind create cluster --config kind-config.yaml
```

<pre>
可能的错误

Creating cluster "kind" ... ERROR: failed to create cluster: failed to ensure docker network:
command "docker network create -d=bridge -o com.docker.network.bridge.enable_ip_masquerade=true -o
com.docker.network.driver.mtu=1500 --ipv6 --subnet fc00:f853:ccd:e793::/64 kind" failed with error:
exit status 1 Command Output: Error response from daemon: could not find an available,
non-overlapping IPv4 address pool among the defaults to assign to the network

遇到后可以手动先执行
docker network create -d=bridge --subnet=172.19.0.0/24 kind
</pre>

## 安装kubectl

```shell
https://kubernetes.io/docs/tasks/tools/install-kubectl-linux/#install-kubectl-binary-with-curl-on-linux
```

## kubectl [访问集群](https://kubernetes.io/zh/docs/tasks/access-application-cluster/access-cluster/)

```shell
# 切换集群 kind- 是前缀，kind是默认kind集群的名字
kubectl cluster-info --context kind-kind
# 查看集群信息
kubectl cluster-info
# 查看集群配置 
kubectl config view

```

## 安装dashboard

```shell
# https://kubernetes.io/zh/docs/tasks/access-application-cluster/web-ui-dashboard/
kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v2.2.0/aio/deploy/recommended.yaml
# 打开dashboard 代理端口，可以重复执行
nohup kubectl proxy > /dev/null 2>&1 &
```

### nginx conf

> 新版本的dashboard 要求https 访问

生成证书

```shell
mkdr -p /data/nginx/cert
openssl genrsa -des3 -out server.key 2048
openssl req -new -key server.key -out server.csr
openssl rsa -in server.key -out server.key
# 如果访问证书错误可以下载后导入mac或者window 设置信任
openssl x509 -req -days 365 -in server.csr -signkey server.key -out server.crt
```

创建nginx server配置 k8s-dashboard.conf

```shell
server {
    listen       443 ssl;
    #  ssl on; 新版本已废弃
    ssl_protocols  TLSv1 TLSv1.1 TLSv1.2;
    ssl_ciphers  AES128-SHA:AES256-SHA:RC4-SHA:DES-CBC3-SHA:RC4-MD5;
    # 主目录是/etc/nginx/
    ssl_certificate  conf.d/cert/server.crt;
    ssl_certificate_key  conf.d/cert/server.key;
    ssl_session_cache  shared:SSL:10m;
    ssl_session_timeout  10m;

    location / {
        proxy_pass http://127.0.0.1:8001/api/v1/namespaces/kubernetes-dashboard/services/https:kubernetes-dashboard:/proxy/;
    }
  }
```

### 安装nginx

```shell
# https://hub.docker.com/_/nginx?tab=description
docker pull nginx
# 不用-p 映射80端口，host 模式下自动使用dockerfile 中expose 的端口
docker create --name nginx --network host -v /data/nginx:/etc/nginx/conf.d nginx
# 等nginx的配置处理好了再启动，因为没有默认配置文件，他内部会生成一个默认的且无法覆盖
docker start nginx
# 或者一步到位(需要先把nginx的配置正好)
docker run --name nginx --network host -v /data/nginx:/etc/nginx/conf.d nginx -d
# 有问题可以进去看看里面有nginx的样本配置
# https://github.com/nginx/nginx/blob/master/conf/nginx.conf
docker exec -it nginx bash 
# 查看日志
docker logs [-f] nginx 
```

## token 访问

> 新版dashboard 默认不允许匿名访问，可以选择创建创号或者使用默认的

```shell
kubectl -n kube-system describe $(kubectl -n kube-system get secret -n kube-system -o name | grep namespace) | grep token
```




