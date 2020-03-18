package com.graves.meetingfilm.hall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.graves.meetingfilm"})
@MapperScan(basePackages = {"com.graves.meetingfilm.hall.dao"})
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class BackendHallApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendHallApplication.class, args);
    }

}
