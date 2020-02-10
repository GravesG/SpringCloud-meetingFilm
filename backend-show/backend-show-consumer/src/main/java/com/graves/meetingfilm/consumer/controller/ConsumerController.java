package com.graves.meetingfilm.consumer.controller;

import com.graves.meetingfilm.consumer.service.ConsumerServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Graves
 * @title: ConsumerController
 * @projectName backend-parent
 * @description: TODO
 * @date 2020/2/10 21:59
 */
@RestController
public class ConsumerController {

    @Autowired
    private ConsumerServiceAPI consumerServiceAPI;

    @RequestMapping(value = "/sayhello")
    public String sayHello(String message){
        return consumerServiceAPI.sayHello(message);
    }
}
