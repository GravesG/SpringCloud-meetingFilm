package com.graves.meetingfilm.consumer.feign;

import org.springframework.stereotype.Service;

/**
 * @author Graves
 * @title: FallbackFactory
 * @projectName backend-parent
 * @description: TODO
 * @date 2020/3/17 21:29
 */
@Service
public class FallbackFactory implements feign.hystrix.FallbackFactory {

    @Override
    public ProviderApi create(Throwable throwable) {
        return new ProviderApi() {
            @Override
            public String invokeProviderController(String message) {
                return "invokerProviderController FallbackFactory message="+message;
            }
        };
    }
}
