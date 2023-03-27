package com.toast.provider.emp.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;

/**
 * @author 土司先生
 * @time 2023/3/27
 * @describe
 */
@Configuration
public class SwaggerConfig {
    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder().title("【雇员部门】部门微服务")
                .description("三更灯火五更鸡，正式男儿读书时，黑发不知勤学早，白首方悔读书迟 -- 《劝学》-[唐] 颜真卿 ")
                .contact(new Contact("土司边", "", "1234@toast.com"))
                .license("土司-用户管理")
                .version("1.0.0")
                .build();
    }
}
