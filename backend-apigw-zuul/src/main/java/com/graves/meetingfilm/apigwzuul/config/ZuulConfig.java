package com.graves.meetingfilm.apigwzuul.config;

import com.graves.meetingfilm.apigwzuul.filters.MyFilter;
import lombok.Builder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Graves
 * @title: ZuulConfig
 * @projectName backend-parent
 * @description: TODO
 * @date 2020/3/19 23:26
 */
@Configuration
public class ZuulConfig {

    @Bean
    public MyFilter initMyFilter(){
        return new MyFilter();
    }
}
