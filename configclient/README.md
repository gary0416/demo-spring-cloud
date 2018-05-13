# spring cloud config
## client例子
注意client端配置文件名不是application而是bootstrap.

启动服务,访问http://localhost:7061/getTestKey可以看到输出是testKey val is testVal,
可以从config-server中的/config/config-client-dev.yml文件取到testKey的值.

## bus-refresh
用来解决server中的配置文件修改后,client不知道.
通过mq,利用暴露出来的bus-refresh endpoint触发.
注意官网手册http://cloud.spring.io/spring-cloud-static/Finchley.RC1/single/spring-cloud.html#_quick_start_3里写的仍然是旧版本,新版改为bus-refresh

### 手动 
启动eureka server,config server,config client,访问http://localhost:7061/getTestKey,可以看到值是testVal.
打开http://localhost:15672/,可以看到topic为springCloudBus.
修改config server里,配置文件中的testKey值.因为不是git版,所以只能重启config server.
主动触发重新加载配置,post方式访问http://localhost:7061/actuator/bus-refresh.
此时console可以看到会立即Refreshing,然后在eureka里状态改为DOWN,之后UP.稍等后再访问getTestKey可以看到已变为新值.

### 自动
client众多,都手动太麻烦,可通过server里的refresh解决,详见server例子,client保持现状无需修改.
