package com.graves.meetingfilm.consumer.feign;

import org.springframework.stereotype.Service;

/**
 * @author Graves
 * @title: ProviderFallbackAPIImpl
 * @projectName backend-parent
 * @description: TODO
 * @date 2020/3/17 21:25
 */
@Service
public class ProviderFallbackAPIImpl implements ProviderApi {
    @Override
    public String invokeProviderController(String message) {
        return "invokerProviderController fallback message="+message;
    }
}
