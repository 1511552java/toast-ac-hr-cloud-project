package com.toast.provider.access.config;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * @author 土司先生
 * @time 2023/3/22
 * @describe Druid 监控配置类
 */
@Configuration
public class DruidMonitorConfiguration {
    @Bean("druidStatViewServlet")
    public ServletRegistrationBean<StatViewServlet> getDruidStatViewServlet() {
        ServletRegistrationBean<StatViewServlet> registrationBean = new ServletRegistrationBean<>(
                new StatViewServlet(), "/druid/*"); // 程序的访问路径
        registrationBean.addInitParameter(
                StatViewServlet.PARAM_NAME_ALLOW, "127.0.0.1"); // 白名单
        registrationBean.addInitParameter(
                StatViewServlet.PARAM_NAME_DENY, ""); // 黑名单
        registrationBean.addInitParameter(
                StatViewServlet.PARAM_NAME_USERNAME, "muyan"); // 用户名
        registrationBean.addInitParameter(
                StatViewServlet.PARAM_NAME_PASSWORD, "yootk");// 密码
        registrationBean.addInitParameter(
                StatViewServlet.PARAM_NAME_RESET_ENABLE, "true"); // 允许重置
        return registrationBean;
    }

    @Bean
    @DependsOn("webStatFilter")
    public FilterRegistrationBean<WebStatFilter> getDruidWebStatFilter(
            WebStatFilter webStatFilter
    ) {
        FilterRegistrationBean<WebStatFilter> registrationBean = new FilterRegistrationBean<>(webStatFilter);
        registrationBean.addUrlPatterns("/*"); // 对所有的路径都进行监控配置
        registrationBean.addInitParameter(WebStatFilter.PARAM_NAME_EXCLUSIONS,
                "*.js,*.gif,*.jpg,*.bmp,*.css,*.ico,/druid/*"); // 路径排除
        return registrationBean;
    }

    @Bean("webStatFilter")
    public WebStatFilter getWebStatFilter() {   // 获取WEB状态过滤
        WebStatFilter statFilter = new WebStatFilter();
        statFilter.setSessionStatEnable(true); // 对Session状态进行监控
        return statFilter;
    }

    @Bean("sqlStatFilter")
    public StatFilter getSQLStatFilter(
            @Value("${toast.datasource.druid.stat.merge-sql}")
            boolean mergeSql,
            @Value("${toast.datasource.druid.stat.log-slow-sql}")
            boolean logSlowSql,
            @Value("${toast.datasource.druid.stat.slow-sql-millis}")
            long slowSqlMillis
    ) { // 定义关于SQL监控的处理部分
        StatFilter filter = new StatFilter();
        filter.setMergeSql(mergeSql); // 是否需要合并统计
        filter.setLogSlowSql(logSlowSql); // 慢SQL记录
        filter.setSlowSqlMillis(slowSqlMillis); // 慢SQL执行时间
        return filter;
    }

    @Bean("sqlWallConfig")
    public WallConfig getSQLWallConfig() { // SQL防火墙配置
        WallConfig config = new WallConfig();
        config.setMultiStatementAllow(true); // 允许进行多个Statement操作
        config.setDeleteAllow(true); // 允许执行删除操作
        return config;
    }

    @Bean("sqlWallFilter")
    public WallFilter getSQLWallFilter(WallConfig wallConfig) { // 注入防火墙配置项
        WallFilter wallFilter = new WallFilter();
        wallFilter.setConfig(wallConfig);
        return wallFilter;
    }
}
