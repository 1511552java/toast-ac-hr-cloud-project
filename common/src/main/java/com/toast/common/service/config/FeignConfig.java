package com.toast.common.service.config;

import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;

/**
 * @author 土司先生
 * @time 2023/3/24
 * @describe 定义Feign配置类
 */
public class FeignConfig {

    /**
     * 输出完全的日志信息
     */
    @Bean
    public Logger.Level level() {
        return Logger.Level.FULL;
    }

    /**
     * 请求拦截器
     */
    @Bean
    public RequestInterceptor getFeignRequestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply (RequestTemplate template){
                template.header("token", "eyJhdXRob3IiOiJ0b2FzdCIsIm1vZHVsZSI6InN5c3RlbS5hY2Nlc3MiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ7XCJyb2xlc1wiOlt7XCJyaWRcIjpcImVtcFwiLFwibmFtZVwiOlwi6ZuH5ZGY566h55CGXCIsXCJub3RlXCI6XCLlrp7njrDpm4flkZjkv6Hmga_mn6XnnIvkuI7mm7TmlrDmk43kvZxcIn0se1wicmlkXCI6XCJkZXB0XCIsXCJuYW1lXCI6XCLpg6jpl6jnrqHnkIZcIixcIm5vdGVcIjpcIuWunueOsOmDqOmXqOS_oeaBr-afpeeci-S4juabtOaWsOaTjeS9nFwifSx7XCJyaWRcIjpcInJhdGluZ1wiLFwibmFtZVwiOlwi57qn5Yir566h55CGXCIsXCJub3RlXCI6XCLlrp7njrDpm4flkZjogYzkvY3nuqfliKvnrqHnkIZcIn0se1wicmlkXCI6XCJtZW1iZXJcIixcIm5hbWVcIjpcIueUqOaIt-euoeeQhlwiLFwibm90ZVwiOlwi5a6e546w566h55CG55So5oi355qE5L-h5oGv566h55CGXCJ9LHtcInJpZFwiOlwicmVjb3JkXCIsXCJuYW1lXCI6XCLorrDlvZXnrqHnkIZcIixcIm5vdGVcIjpcIuWunueOsOaVsOaNruabtOaWsOiusOW9leeahOi3n-i4qlwifV0sXCJuYW1lXCI6XCLkurrlipvotYTmupDnrqHnkIbns7vnu59cIixcIm1pZFwiOlwiaHJcIixcImFjdGlvbnNcIjpbXCJkZXB0OmVkaXRcIixcImVtcDplZGl0XCIsXCJtZW1iZXI6YWRkXCIsXCJkZXB0OmFkZFwiLFwibWVtYmVyOmVkaXRcIixcInJhdGluZzpsaXN0XCIsXCJtZW1iZXI6bGlzdFwiLFwiZW1wOmRlbGV0ZVwiLFwiZW1wOmxpc3RcIixcImRlcHQ6bGlzdFwiLFwicmF0aW5nOmFkZFwiLFwicmF0aW5nOmRlbGV0ZVwiLFwiZW1wOmFkZFwiLFwiZGVwdDpkZWxldGVcIixcIm1lbWJlcjpkZWxldGVcIixcInJlY29yZDphZGRcIixcInJhdGluZzplZGl0XCIsXCJyZWNvcmQ6bGlzdFwiXX0iLCJzaXRlIjoidG9hc3RfYnJlYWQiLCJpc3MiOiJ0b2FzdF9icmVhZCIsIm1lc3NhZ2UiOiLlnJ_lj7jpnaLljIUiLCJleHAiOjE2ODA1MTE3NTEsImlhdCI6MTY3OTY0Nzc1MSwibmljZSI6Ikdvb2QgR29vZCBHb29kIiwianRpIjoic3lzdGVtLWFjY2Vzcy1hMGY1OThlNC03YTRhLTQ2NzMtYjgyMi03NWQ1OTU1NmI1YTgifQ.NemTVdpm34vi7qRJOMcqJMpzF4TZ0lTtGcZsjz4rfEg");
            }
        };
    }
}
