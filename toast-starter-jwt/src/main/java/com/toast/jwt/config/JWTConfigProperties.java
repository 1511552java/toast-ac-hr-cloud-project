package com.toast.jwt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 土司先生
 * @time 2023/3/21
 * @describe JWT配置信息
 */
@Data
@ConfigurationProperties(prefix = "toast.security.config.jwt")
public class JWTConfigProperties {
    /**
     * 保存签名信息
     */
    private String sign;

    /**
     * 证书签发者
     */
    private String issuer;

    /**
     * 加密密钥
     */
    private String secret;

    /**
     * 失效时间
     */
    private long expire;
}
