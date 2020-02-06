package com.graves.meetingfilm.user.controller;

import com.graves.meetingfilm.user.controller.vo.LoginReqVO;
import com.graves.meetingfilm.utils.exception.CommonServiceException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户模块展示层
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody LoginReqVO reqVO) throws CommonServiceException {
        //数据验证
        reqVO.checkParam();
        return null;
    }

}
