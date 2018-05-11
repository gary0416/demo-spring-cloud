# feign
## feign简介
Feign是一个声明式的伪Http客户端，它使得写Http客户端变得更简单。使用Feign，只需要创建一个接口并注解。它具有可插拔的注解特性，可使用Feign 注解和JAX-RS注解。Feign支持可插拔的编码器和解码器。Feign默认集成了Ribbon，并和Eureka结合，默认实现了负载均衡的效果。

简而言之：
- Feign 采用的是基于接口的注解
- Feign 整合了ribbon

注意pom里需引入spring-cloud-starter-openfeign,原spring-cloud-starter-feign已废弃

## 例子
启动eureka server,再起两个service core,此时可以看到SERVICE-CORE的Availability Zones有2个,启动本项目,访问http://localhost:8381/getDataFromCore多次,可以看到每次通过负载均衡,在切换访问不同的service实例.

## Hystrix Dashboard
打开http://localhost:8381/hystrix,输入http://localhost:8381/hystrix.stream,点monitor stream,
