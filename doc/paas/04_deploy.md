# 应用编排

## Node Label

先对node用标签的方式进行分类 规划标签：

4 server、 2 store、 2 nginx 、2 monitor

```
application: server|store|nginx|monitor
```

```shell
# 先查看node
kubectl get nodes  -o wide [--selector key=value]
# 查看节点标签
kubectl get nodes [node-name] --show-labels 
# 删除标签key 上写个-
kubectl label node kind-worker application-

kubectl label node kind-worker application=server
kubectl label node kind-worker2 application=server
kubectl label node kind-worker3 application=server
kubectl label node kind-worker4 application=server

kubectl label node kind-worker5 application=store
kubectl label node kind-worker6 application=store

kubectl label node kind-worker7 application=nginx
kubectl label node kind-worker8 application=nginx

kubectl label node kind-worker9 application=monitor
kubectl label node kind-worker10 application=monitor
```

## k8s 公共知识

1、metadata 决定一个资源的唯一性

2、用kind需要load 镜像

3、常用命令

```shell
# 部门缩写 个人习惯全写免得混淆 deployments == deploy nodes == no services == svc
kubectl get all -n namespace
kubectl get deployments|nodes|pods|services|rs|configMap [name]  [-A 全部空间] [-n 指定空间] -o wide [--show-labels 展示标签] 
kubectl describe deployments|nodes|pods|services|rs|configMap  name -n 空间
kubectl delete deployments|nodes|pods|services|rs|configMap name -n namespace
kubectl edit deployments|nodes|pods|services|rs|configMap name -n namespace
kubectl exec pod名称 -it -n 空间 -- bash(命令)
kubectl logs podname [-f] -n 空间
```

```shell
kind load docker-image xx:tag xx2:tag
```

## 部署

```shell
# 创建或者便捷都是
kubectl apply -f  config_map.yaml deployment.yaml service.yaml
# 删除
kubectl delete -f  deployment.yaml

```

## ingress 控制器

控制器种类很多 https://kubernetes.io/zh/docs/concepts/services-networking/ingress-controllers/
> 当需要网关服务时候

```shell
# https://kubernetes.github.io/ingress-nginx/deploy/
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v1.1.0/deploy/static/provider/cloud/deploy.yaml
```