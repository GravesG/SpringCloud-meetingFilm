package com.graves.meetingfilm.consumer.feign;

import com.graves.meetingfilm.consumer.feign.vo.UserModel;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @author Graves
 * @title: ProviderAPIImpl
 * @projectName backend-parent
 * @description: TODO
 * @date 2020/3/16 21:41
 */
//@Primary
@Service
public class ProviderAPIImpl implements ProviderApi {
    @Override
    public String invokeProviderController(String message) {
        return null;
    }

//    @Override
//    public String providerPost(String author, String providerId, UserModel userModel) {
//        return null;
//    }
}
