# spring cloud config
## client例子
注意client端配置文件名不是application而是bootstrap.

启动服务,访问http://localhost:7061/getTestKey可以看到输出是testKey val is testVal,
可以从config-server中的/config/config-client-dev.yml文件取到testKey的值.