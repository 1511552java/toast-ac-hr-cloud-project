package com.toast.gateway;

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
public class StartGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(StartGatewayApplication.class, args);
    }
}
