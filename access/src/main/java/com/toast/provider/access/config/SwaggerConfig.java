package com.toast.provider.access.config;

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
 * @time 2023/3/22
 * @describe
 */
@Configuration
public class SwaggerConfig {
    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder().title("【土司面包】部门微服务")
                .description("我只吃你做的土司面包，因为只有你做的土司面包我才会有灵感")
                .contact(new Contact("土司边", "", "1234@toast.com"))
                .license("土司-授权管理")
                .version("1.0.0")
                .build();
    }
    @Bean
    public Docket getDocket() { // 配置Swagger
        return new Docket(DocumentationType.SWAGGER_2) // 文档类型
                .apiInfo(this.getApiInfo()) // 接口描述
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.toast.provider.access.controller")) // 扫描包
                .paths(PathSelectors.any()).build();
    }
}
