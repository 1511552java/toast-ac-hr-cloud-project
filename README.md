# hr-cloud-project

​		练习项目：hr-cloud-project。原教程：https://www.bilibili.com/video/BV1yu411i74m/?spm_id_from=333.999.0.0&vd_source=4496e55e7ee633a5ac9331ab94763cb9  。 一位大佬技术的教程，值得一看。

感想：

​        小编把项目重头到尾从新写的一遍。以“toast”为包的核心名称。体会到了微服务之间的运作。前提是要先掌握SpringCloudAlibaba套组件。或者有Netfilx的经验也可以(不过有经验的应该不会重新搞这个吧)

hr-cloud-project项目：用于练习spring-cloud-alibaba套件；具体介绍如下：

>hr-cloud-project
>├─access                       系统接入服务
>├─common                   common公共模块
>├─endpoint                   服务消费端/服务客户端
>├─gateway                     网关路由服务
>├─provider-dept           部门管理服务
>├─provider-emp           雇员管理服务
>├─provider-member    用户管理服务  
>├─provider-rating        工资等级管理服务
>├─provider-record       日志采集管理
>├─toast-starter-jwt       jwt认证服务
>└─util                              util模块



项目采用Nacos负责服务发现与服务注册

使用SpringCloudGateway作为路由转发网关

Sentinel负责熔断，限流操作处理

使用Feign实现接口远程调用服务, Ribbon 负责负载均衡处理。

使用Seata负载分布式事务

提供

access（系统接入服务），

provider-dept(部门管理)，

provider-emp(雇员管理)，

provider-member(用户管理),

provider-rating(工资等级管理服务)，

provider-record(日志采集管理服务), 

toast-starter-record (jwt认证服务) 

以上服务都有各自的数据库。所以分布式之间的事务交由seata负责。

综上服务一共有

access（系统接入服务），

provider-dept(部门管理)，

provider-emp(雇员管理)，

provider-member(用户管理),

provider-rating(工资等级管理服务)，

provider-record(日志采集管理服务), 

toast-starter-record (jwt认证服务) ，

Nacos,

Sentinel,

ReocktMQ,

gateway

endpoint,

该项目大概有12 个服务节点。



