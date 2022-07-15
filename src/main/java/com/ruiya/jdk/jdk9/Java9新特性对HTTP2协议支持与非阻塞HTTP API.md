## 对HTTP2协议的支持与非阻塞HTTP-API
- 在HTTP/1.1 发布了16 年之后，IETF在2015年终于通过了HTTP/2 协议。HTTP/2协议旨在降低延迟，满足当今时代对于信息响应时间的要求。在这篇文章中，我会简要的对HTTP/2协议进行介绍，然后我们将重点放在研究Java9中对HTTP/2支持及其HTTP客户端API的变化。

### 一、HTTP/2简介
- HTTP/2 旨在减轻 HTTP/1.1 维护复杂基础结构所造成的痛苦，性能良好。尽管 HTTP/2 仍然与 HTTP/1.1 向后兼容，但它不再是基于文本的协议。
- HTTP/2 多路复用使单个连接可以处理多个双向流，允许客户端通过单个连接同时下载多个资源。
- HTTP 1.x 协议是基于文本的，因此报文很冗长。有的时候，同一组 HTTP Headers被一遍又一遍地交换。HTTP/2 通过跨请求维护 HTTP Headers，消除重复交换的数据，大大减少了数据交互所需的带宽。
#### HTTP/2数据推送
HTTP/2 推送是主动向客户端发送资源，而无需从客户端的角度发起资源请求。这意味着服务器端根据一个请求可能知道网站进一步需要的其他资源，并且早在客户端再次发起请求它们之前，就可以一并（提前）发送所有资源。

#### 目前支持 HTTP/2 的 Java HTTP 客户端
- Jetty 
- Netty 
- OkHttp 
- Vert.x 
- Firefly

### 二、Java 9 的 HTTP/2 客户端
- 首先使用Java 9的语法进行模块的导入 。jdk.incubator.httpclient
```java
module com.springui.echo.client {
    requires jdk.incubator.httpclient;
}
```
- Java 9 新的 HTTP Cient API 遵循构建器模式。HttpClient是用来操作HTTP请求的入口点，先构建后使用。
```java
HttpClient client = HttpClient
    .newBuilder()
    .version(Version.HTTP_2)  //支持HTTP2
    .build();
```






