package com.toast.gateway.config;

import com.alibaba.nacos.api.PropertyKeyConst;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @author 土司先生
 * @time 2023/3/23
 * @describe
 */
@Component
@Data
@ConfigurationProperties(prefix = "spring.cloud.nacos.discovery")
public class GatewayNacosConfig {
    private String serverAddr;
    private String namespace;
    private String group;
    private String username;
    private String password;
    private String dataId = "gateway.config";
    private long timeout = 2000;
    public Properties getNacosProperties() {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, this.serverAddr);
        properties.put(PropertyKeyConst.NAMESPACE, this.namespace);
        properties.put(PropertyKeyConst.USERNAME, this.username);
        properties.put(PropertyKeyConst.PASSWORD, this.password);
        return properties;
    }
}
