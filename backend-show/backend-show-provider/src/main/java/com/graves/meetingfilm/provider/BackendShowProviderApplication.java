package com.graves.meetingfilm.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableDiscoveryClient // 可以集成大部分注册中心
// @EnableEurekaClient 直对Eureka有用
@SpringBootApplication
public class BackendShowProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendShowProviderApplication.class, args);
    }

}
