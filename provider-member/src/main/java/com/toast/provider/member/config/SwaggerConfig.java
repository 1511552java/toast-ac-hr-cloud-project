package com.toast.provider.member.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author 土司先生
 * @time 2023/3/24
 * @describe
 */
@Configuration
public class SwaggerConfig {
    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder().title("【土司面包】部门微服务")
                .description("古人学问无遗力，少壮功夫老始成。纸上得来终觉浅，绝只此事要躬行 -- 《冬夜读书示子聿·选一》[宋]陆游")
                .contact(new Contact("土司边", "", "1234@toast.com"))
                .license("土司-用户管理")
                .version("1.0.0")
                .build();
    }

    @Bean
    public Docket getDocket() { // 配置Swagger
        return new Docket(DocumentationType.SWAGGER_2) // 文档类型
                .apiInfo(this.getApiInfo()) // 接口描述
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.toast.provider.member.controller")) // 扫描包
                .paths(PathSelectors.any()).build();
    }
}
