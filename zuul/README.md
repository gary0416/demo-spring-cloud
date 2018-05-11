# Zuul
## Zuul简介
Zuul的主要功能是路由转发和过滤器。路由功能是微服务的一部分，比如/api/user转发到到user服务，/api/shop转发到到shop服务。zuul默认和Ribbon结合实现了负载均衡的功能。

zuul有以下功能：
- Authentication
- Insights
- Stress Testing
- Canary Testing
- Dynamic Routing
- Service Migration
- Load Shedding
- Security
- Static Response handling
- Active/Active traffic management

## 例子
启动eureka server,再起各启动1个service core和service user,启动本项目.

直接访问http://localhost:8481/api-core/会被拦截,显示401 access forbidden

访问http://localhost:8481/api-core/?access_token=1,可以看到访问的是core,显示service service-core on port 8081.

访问http://localhost:8481/api-user/?access_token=1,可以看到访问的是user,显示service service-user on port 8181.

## filter
详见SecurityFilter

## fallback
启动eureka server,再启动service user,启动本项目.

访问http://localhost:8481/api-user/?access_token=1,可以正常访问

访问http://localhost:8481/api-core/?access_token=1,因core项目没启动,根据CoreFallbackProvider,返回:service-core is unavailable, please try it again later.
