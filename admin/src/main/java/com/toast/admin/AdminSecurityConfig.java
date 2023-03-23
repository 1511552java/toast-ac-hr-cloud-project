package com.toast.admin;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

/**
 * @author 土司先生
 * @time 2023/3/23
 * @describe 配置spring-security的信息
 */
@Configuration
public class AdminSecurityConfig extends WebSecurityConfigurerAdapter {
    private String adminContextPath;
    public AdminSecurityConfig(AdminServerProperties adminServerProperties) {
        this.adminContextPath = adminServerProperties.getContextPath(); // 上下文的处理路径
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        SavedRequestAwareAuthenticationSuccessHandler successHandler =
                new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("redirectTo");
        http.authorizeRequests()
                .antMatchers(this.adminContextPath + "/assets/**").permitAll()
                .antMatchers(this.adminContextPath + "/login").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage(this.adminContextPath + "/login")
                .successHandler(successHandler)
                .and().logout().logoutUrl(this.adminContextPath + "/logout")
                .and().httpBasic().and().csrf().disable();
    }
}
