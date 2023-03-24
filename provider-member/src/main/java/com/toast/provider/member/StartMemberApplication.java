package com.toast.provider.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 土司先生
 * @time 2023/3/24
 * @describe
 */
@EnableDiscoveryClient
@SpringBootApplication
public class StartMemberApplication {
    public static void main(String[] args) {
        SpringApplication.run(StartMemberApplication.class, args);
    }
}
