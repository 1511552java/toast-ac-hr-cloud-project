package com.toast.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 土司先生
 * @time 2023/3/23
 * @describe 网关配置项
 */
@Data
@Component
@ConfigurationProperties(prefix = "gateway.config.jwt")
public class GatewayJWTConfigProperties {
    /**
     * 配置跳过路径
     */
    private List<String> skipAuthUrls;
    /**
     * 配置头信息名称
     */
    private String headerName;
}
