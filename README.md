# hr-cloud-project



参考沐言优拓-李兴华老师的springcloud项目实战。将项目的构建开始编写一遍代码。李老师的项目基本都以 yootk 来作为名称。我的重写项目使用 toast (土司 我的笔名)作为名称搭建。其余内容基本一致

项目开发环境：

​       JDK11, MYSQL8,  SpringBoot.2.2.6, SpringCloudAlibaba组件套

01 - 【构建Gradle项目目录结构】

提供 build.gradle, settings.gradle, dependencies.gradle（非核心配置文件）



02 - 新增【util】工具模块；提供MD5加密，提供基于base64的加密解密

03 - 新增【toast-hr-jwt】模块; 使用JWT进行各个服务节点之间授权认证管理，而且是现阶段最轻量的数据验证服务，早期的SpringCloudNetflix使用OAuth2，但是随着SpringCloud版本升级，以及各种配置抵触，spring默认不推荐使用这种方式



