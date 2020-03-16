package com.graves.meetingfilm.feignconf;

import feign.Contract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Graves
 * @title: FeignHelloConf
 * @projectName backend-parent
 * @description: TODO
 * @date 2020/3/16 22:03
 */
@Configuration
public class FeignHelloConf {

    @Bean
    public Contract contract() {
        return new feign.Contract.Default();
    }
}
