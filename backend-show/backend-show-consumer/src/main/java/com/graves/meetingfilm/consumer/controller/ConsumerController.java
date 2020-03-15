package com.graves.meetingfilm.consumer.controller;

import com.graves.meetingfilm.consumer.feign.ProviderApi;
import com.graves.meetingfilm.consumer.feign.vo.UserModel;
import com.graves.meetingfilm.consumer.service.ConsumerServiceAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Graves
 * @title: ConsumerController
 * @projectName backend-parent
 * @description: TODO
 * @date 2020/2/10 21:59
 */
@RestController
@Slf4j
public class ConsumerController {

    @Autowired
    private ConsumerServiceAPI consumerServiceAPI;

    @Resource
    private ProviderApi providerApi;

    @RequestMapping(value = "/sayhello")
    public String sayHello(String message){
        return consumerServiceAPI.sayHello(message);
    }

    @RequestMapping(value = "/sayhello/feign")
    public String sayHelloFeign(String message){
        System.out.println("feign message=" + message);
        return providerApi.invokeProviderController(message);
    }

    @RequestMapping(value = "/sayhello/post")
    public String sayHelloPost(String author, String providerId, UserModel userModel){
        log.info("author:{}, providerId:{}, userModel:{}",author,providerId,userModel);
        return providerApi.providerPost(author,providerId,userModel);
    }
}
