package com.toast.jwt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 土司先生
 * @time 2023/3/21
 * @describe 加密属性配置
 */
@Data
@ConfigurationProperties(prefix = "toast.security.config.password")
public class EncryptConfigProperties {
    /**
     * 定义重复的次数
     */
    private Integer repeat;

    /**
     * 盐值
     */
    private String salt;
}
