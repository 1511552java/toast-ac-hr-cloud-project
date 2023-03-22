package com.toast.provider.access;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 土司先生
 * @time 2023/3/22
 * @describe
 */
@SpringBootApplication
@EnableDiscoveryClient
public class StartAccessApplication {
    public static void main(String[] args) {
        SpringApplication.run(StartAccessApplication.class, args);
    }
}
