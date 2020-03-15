package com.graves.meetingfilm.consumer.feign;

import com.graves.meetingfilm.consumer.feign.vo.UserModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author Graves
 * @title: ProviderApi
 * @projectName backend-parent
 * @description: TODO
 * @date 2020/3/15 22:37
 */

@FeignClient(name = "providerTest", url = "http://localhost:7101")
public interface ProviderApi {

    @RequestMapping(value = "/provider/sayhello",method = RequestMethod.GET)
    String invokeProviderController(@RequestParam("message") String message);

    @RequestMapping(value = "/provider/{providerId}/sayhello",method = RequestMethod.POST)
    String providerPost(
            @RequestHeader("author") String author,
            @PathVariable("providerId")String providerId,
            @RequestBody UserModel userModel);
}
