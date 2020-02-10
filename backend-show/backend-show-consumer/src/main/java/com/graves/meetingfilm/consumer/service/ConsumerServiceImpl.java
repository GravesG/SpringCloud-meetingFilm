package com.graves.meetingfilm.consumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancedRetryContext;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Graves
 * @title: ConsumerServiceImpl
 * @projectName backend-parent
 * @description: TODO
 * @date 2020/2/10 21:55
 */
@Service
public class ConsumerServiceImpl implements ConsumerServiceAPI {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient eurekaCliend;

    @Override
    public String sayHello(String message) {

        /*String hostname = "localhost";
        int port = 7101;
        String uri = "/provider/sayhello?message="+message;*/

        ServiceInstance choose = eurekaCliend.choose("hello-service-provider");
        String hostname = choose.getHost();
        int port = choose.getPort();
        String uri = "/provider/sayhello?message="+message;

        String url = "http://" + hostname + ":" + port + uri;

        String res = restTemplate.getForObject(url,String.class);

        return res;
    }
}
