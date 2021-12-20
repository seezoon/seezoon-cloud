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
grpcurl -plaintext -d '{"name":"dfenghuang"}' localhost:9000 com.seezoon.helloworld.HelloWorld/Say 
```


