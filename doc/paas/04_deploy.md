# 应用编排

## Node Label

先对node用标签的方式进行分类 规划标签：

4 server、 2 store、 2 nginx 、2 monitor

```
application: server|store|nginx|monitor
```

```shell
# 先查看node
kubectl get nodes  -o wide
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

2 、用kind需要load 镜像

```shell
kind load docker-image xx:tag xx2:tag
```

## 部署

```shell
# 创建或者便捷都是
kubectl apply -f  config_map.yaml deployment.yaml service.yaml
# 删除
kubectl delete -f  deployment.yaml
# 按名字删除 ,其他工作负载类似
kubectl get deployment|pod|service -n namespace
kubectl delete  deployment|pod|service name -n namespace


```
