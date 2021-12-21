# 测试工具

```shell
#https://github.com/fullstorydev/grpcui
go install github.com/fullstorydev/grpcui/cmd/grpcui@latest 
# https://github.com/fullstorydev/grpcurl
go install github.com/fullstorydev/grpcurl/cmd/grpcurl@latest
```

## 常用命令

### grpcui

grpcui -plaintext localhost:9000

### grpcurl

```shell
# 直接访问
grpcurl -plaintext -d '{"name":"dfenghuang"}' localhost:9000 com.seezoon.helloworld.HelloWorld/Say 
# ingress 不支持明文访问有证书
grpcurl -insecure -max-time 1 -d '{"name":"dfenghuang"}' server.seezoon.com:30443 com.seezoon.helloworld.HelloWorld/Say 
kubectl logs -f ingress-nginx-controller-7d577c856-rhszn -n ingress-nginx
kubectl exec -it ingress-nginx-controller-7d577c856-rhszn -n ingress-nginx -- bash
kubectl port-forward service/ingress-nginx-controller -n ingress-nginx --address 0.0.0.0 30443:443

```


