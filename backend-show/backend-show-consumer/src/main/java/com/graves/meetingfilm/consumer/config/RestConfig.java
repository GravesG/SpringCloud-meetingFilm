package com.graves.meetingfilm.consumer.config;

import com.graves.meetingfilm.consumer.ribbon.rules.MyRule;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Graves
 * @title: RestConfig
 * @projectName backend-parent
 * @description: TODO
 * @date 2020/2/10 22:01
 */
@Configuration
public class RestConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    /**
    * @Author Graves
    * @Description //负载均衡的规则
    * @Date  23:19
    * @Param []
    * @return com.netflix.loadbalancer.IRule
    */
    @Bean
    public IRule iRule(){
        return new MyRule();
    }
}
