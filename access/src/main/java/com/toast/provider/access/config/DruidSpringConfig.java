package com.toast.provider.access.config;

import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author 土司先生
 * @time 2023/3/22
 * @describe Druid 配置Spring监控类
 */
@Configuration
public class DruidSpringConfig {
    @Bean("druidStatInterceptor")
    public DruidStatInterceptor getDruidStatInterceptor() {
        DruidStatInterceptor interceptor = new DruidStatInterceptor(); // 创建Druid拦截器
        return interceptor;
    }
    @Bean("druidSpringStatPointcut")
    @Scope("prototype")
    public JdkRegexpMethodPointcut getDruidSpringStatPointcut() {   // 获取切面
        JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
        pointcut.setPatterns("com.yootk.provider.member.service.*",
                "com.yootk.provider.member.action.*",
                "com.yootk.provider.member.dao.*");
        return pointcut;
    }
    @Bean("druidSpringStatAdvisor")
    public DefaultPointcutAdvisor getDruidSpringStatAdvisor(
            DruidStatInterceptor druidStatInterceptor,
            JdkRegexpMethodPointcut jdkRegexpMethodPointcut
    ) {
        DefaultPointcutAdvisor defaultPointcutAdvisor = new DefaultPointcutAdvisor();
        defaultPointcutAdvisor.setPointcut(jdkRegexpMethodPointcut);
        defaultPointcutAdvisor.setAdvice(druidStatInterceptor);
        return defaultPointcutAdvisor;
    }
}
