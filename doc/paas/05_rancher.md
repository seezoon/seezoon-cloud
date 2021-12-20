# 集群管理工具

## [安装](https://rancher.com/quick-start)

```shell
# 正常可以不加tag 是因为最新版有bug
sudo docker run --name rancher --privileged -d --restart=unless-stopped -p 81:80 -p 446:443 rancher/rancher:v2.6.2-rc2
# 访问 https://host:446
```

## 初次的账号密码

<pre>
It looks like this is your first time visiting Rancher; if you pre-set your own bootstrap password, enter it here. Otherwise a random one has been generated for you. To find it:

For a "docker run" installation:
Find your container ID with docker ps, then run:
docker logs container-id 2>&1 | grep "Bootstrap Password:"

For a Helm installation, run:

kubectl get secret --namespace cattle-system bootstrap-secret -o go-template='{{.data.bootstrapPassword|base64decode}}{{"\n"}}'
</pre>

## 设置账号密码

user:admin password:123456

## 按操作导入以后集群