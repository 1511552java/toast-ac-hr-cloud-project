package com.toast.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 土司先生
 * @time 2023/3/23
 * @describe
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableAdminServer // 启动应用管理
public class StartSpringBootAdmin {
    public static void main(String[] args) {
        SpringApplication.run(StartSpringBootAdmin.class, args);
    }
}
