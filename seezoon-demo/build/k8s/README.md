# demo 说明

## client->server 直连

访问server的k8s service域名即可以

## client->ingress->server

> 模拟将多个服务隐藏与网关直线，通过网关的形式提供统一的服务

## curl->ingress>client->server

> 模拟c端用户通过ingress 访问服务，ingress 分配到具有公网IP的node上