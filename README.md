# hr-cloud-project

hr-cloud-project项目：用于练习spring-cloud-alibaba套件；具体介绍如下：

开发环境：

​		JDK11, Gradle7.6, SpringBoot2, SpringCloudAlibaba;

# hr-cloud-project 结构

```
├─access                  系统接入服务
├─admin                   spring-boot-admin监控服务
├─common                  common公共模块
├─endpoint                服务消费端/服务客户端
├─gateway                 网关路由服务
├─provider-dept           部门管理服务
├─provider-emp            雇员管理服务
├─provider-member         用户管理服务  
├─provider-rating         工资等级管理服务
├─provider-record         日志采集管理
├─toast-starter-jwt       jwt认证服务
└─util                    util模块
```



运行截图如下：

![nacos服务控制台](material\image\002.png)



![](material\image\003.png)

![](material\image\001.png)



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



# access 服务接入模块

​        每一个系统都会有一个Access的注册信息，这个注册信息必须在系统正式接入的时候才允许提供，有了响应的授权信息之后，在进行**Token生成的时候就可以通过系统注册的aid编号查询到对应的角色和权限**。

​		**这样对每一个用户访问服务提供角色权限管理。实现用户与服务资源的隔离。与服务资源的保护。**



# spring-boot-admin

​		引入spring-boot-admin服务资源监控组件。每一个服务资源配置spring-boot-admin的服务监控。毕竟微服务环境当中，每一个服务节点都应该被实时监控到。说到监控业务方面。同例中；像**Spring-boot-actuator + prometheus + Grafana + Alerting**一套实现对服务的监控。还有**ELK （ Elasticsearch、Logstrash 和 Kibana）进行系统服务日志采集管理。**

 

# gateway 路由网关服务

​		为服务配置路由网关服务。这样以后访问都可以不用直接访问服务的主机域名，而是统一访问gateway 路由网关服务。通过路由网关进行请求的转发。实现了服务请求的统一管理。



# provider-member 用户模块服务

​		内部用户管理。包括用户信息，权限信息，角色信息。还包括登录操作。一般企业级别登录都包含很多种情况，用户密码登录。手机号登录。第三方授权登录。可以单独列出一个auth-modle模块。



# provider-dept 部门服务

​		提供部门的增删改查。还有部门领导信息变更，部门人数自增，部门人数的自减





# provider-rating 工资等级服务

​		工资等级薪酬业务提供provider-rating微服务模块，由Endpoint子模块进行远程接口调用(Feign)



# provider-record 日志采集模块

​		Springboot单体架构中的日志是在本应用程序中业务层进行更新日志的数据采集操作。但是现在在SpringCloud过程之中，日志采集的就可以基于消息组件的模块进行完成。

​		采用RocketMQ进行消息的接收与发送。endpoing 产生的日志信息都存储至消息组件中，RocketMQ 将消息推送至provider-record  模块中。

​		SpringCloudStream 是一款微服务辅助技术的产物。SpringCloudStream为了方便解决不同服务之间的业务通信。

​		比如：平台A 和 平台 B 采用  RocketMQ进行消息之间通信。之后平台 A  需要和 平台 C 进行对接。采用的消息组件是 Kafka 。此时平台A 需要编写两套消息组件消息推送与消费。因此。SpringCloudStream将常见的消息组件进行一层包装。提供统一的接受和发送的接口。直接用这一个接口即可。无需编写多余的消息推送与消费的代码。

​		目前SpringCloudStream支持的消息组件：RocketMQ, RabbitMQ,  RocketMQ;三种消息组件。



​		后期考虑替换成 ELK 



# provider-emp 雇员管理服务

​		管理雇员。提供增删改查操作。获取部门人数，清空部门列表

