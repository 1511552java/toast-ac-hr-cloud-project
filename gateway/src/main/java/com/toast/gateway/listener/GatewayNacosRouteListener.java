package com.toast.gateway.listener;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toast.gateway.config.GatewayNacosConfig;
import com.toast.gateway.service.DynamicRouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

/**
 * @author 土司先生
 * @time 2023/3/23
 * @describe
 */
@Slf4j
@Component
public class GatewayNacosRouteListener implements CommandLineRunner {
    @Autowired
    private DynamicRouteService dynamicRouteService; // 设置业务层处理
    @Autowired
    private GatewayNacosConfig nacosConfig; // Nacos服务配置
    // 因为Nacos里面保存的数据类型是JSON数据，所以需要对JSON进行解析，直接使用Jackson组件了
    @Autowired
    private ObjectMapper mapper; // 获取Jackson组件

    @Override
    public void run(String... args) throws Exception {
        this.nacosDynamicRouteListener();// 启动时加载配置
    }

    /**
     * 动态路由监听
     */
    public void nacosDynamicRouteListener() {
        try {
            ConfigService configService = NacosFactory.createConfigService(this.nacosConfig.getNacosProperties());
            String content = configService.getConfig(this.nacosConfig.getDataId(), this.nacosConfig.getGroup(), this.nacosConfig.getTimeout()); // 获取指定的配置项
            log.info("【网关启动】读取Nacos网关配置项：{}", content); // 日志输出
            GatewayNacosRouteListener.this.setRoute(content); // 路由配置
            configService.addListener(this.nacosConfig.getDataId(), this.nacosConfig.getGroup(), new Listener() {
                @Override
                public Executor getExecutor() {
                    return null;
                }

                @Override
                public void receiveConfigInfo(String configInfo) {
                    log.info("【网关更新】读取Nacos网关配置项：{}", configInfo); // 日志输出
                    GatewayNacosRouteListener.this.setRoute(configInfo);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 定义路径处理
     * @param configInfo
     */
    private void setRoute(String configInfo) {
        try {   // 将读取到的数据内容转为路由的配置定义，本操作是由Jackson组件完成的
            RouteDefinition[] routes = this.mapper.readValue(configInfo, RouteDefinition[].class);
            for (RouteDefinition route : routes) {
                this.dynamicRouteService.update(route); // 业务更新
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
