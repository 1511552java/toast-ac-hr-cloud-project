package com.toast.jwt.autoconfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toast.jwt.config.EncryptConfigProperties;
import com.toast.jwt.config.JWTConfigProperties;
import com.toast.jwt.service.ITokenService;
import com.toast.jwt.service.impl.TokenServiceImpl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 土司先生
 * @time 2023/3/21
 * @describe JWT 服务自动配置
 */
@Configuration
@EnableConfigurationProperties({JWTConfigProperties.class, EncryptConfigProperties.class})
public class JWTAutoConfiguration {

    @Bean("encryptConfigProperties")
    public EncryptConfigProperties getEncryptConfigProperties() {
        return new EncryptConfigProperties();
    }

    @Bean("jwtConfigProperties")
    public JWTConfigProperties getJWTConfigProperties() {
        return new JWTConfigProperties();
    }

    @Bean
    public ITokenService getTokenServiceBean() {
        return new TokenServiceImpl();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
