package com.graves.meetingfilm.film;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.graves.meetingfilm"})
@MapperScan(basePackages = {"com.graves.meetingfilm.film.dao"})
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class BackendFilmApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendFilmApplication.class, args);
    }

}
