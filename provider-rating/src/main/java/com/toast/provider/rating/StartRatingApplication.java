package com.toast.provider.rating;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 土司先生
 * @time 2023/3/24
 * @describe
 */
@SpringBootApplication
@EnableDiscoveryClient
public class StartRatingApplication {
    public static void main(String[] args) {
        SpringApplication.run(StartRatingApplication.class, args);
    }
}
