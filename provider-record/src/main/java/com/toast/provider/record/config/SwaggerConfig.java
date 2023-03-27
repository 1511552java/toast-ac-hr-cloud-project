package com.toast.provider.record.config;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author 土司先生
 * @time 2023/3/27
 * @describe
 */
public class SwaggerConfig {
    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder().title("【土司面包】部门微服务")
                .description("花褪残红青杏小，燕子飞时，绿水人家绕。枝上柳绵吹又少。天涯何处无芳草。墙里秋千墙外道，墙外行人，墙里佳人笑。笑渐不闻声渐悄。多情却被无情恼。——宋代·苏轼《蝶恋花·春景》")
                .contact(new Contact("土司边", "", "1234@toast.com"))
                .license("土司-授权管理")
                .version("1.0.0")
                .build();
    }
    @Bean
    public Docket getDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.toast.provider.record.controller"))
                .paths(PathSelectors.any()).build();
    }
}
