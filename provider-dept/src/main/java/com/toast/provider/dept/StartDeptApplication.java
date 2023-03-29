package com.toast.provider.dept;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 土司先生
 * @time 2023/3/29
 * @describe
 */
@SpringBootApplication
@EnableDiscoveryClient
public class StartDeptApplication {
    public static void main(String[] args) {
        SpringApplication.run(StartDeptApplication.class, args);
    }
}
