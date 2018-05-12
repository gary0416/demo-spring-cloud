# hystrix dashboard turbine
## 简介
要想看这个系统的Hystrix Dashboard数据就需要用到Hystrix Turbine。Hystrix Turbine将每个服务Hystrix Dashboard数据进行了整合。

## 例子
启动eureka server,service core,再起被监控的feign和restribbon两个项目

## Hystrix Dashboard
打开http://localhost:7071/hystrix,输入http://localhost:7071/turbine.stream?cluster=default,点monitor stream

通过访问http://localhost:8281/getDataFromCore或http://localhost:8381/getDataFromCore可产生数据,在刚才的网页中能看到统计结果
