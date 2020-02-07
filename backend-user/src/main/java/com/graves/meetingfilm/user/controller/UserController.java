package com.graves.meetingfilm.user.controller;

import com.graves.meetingfilm.user.controller.vo.LoginReqVO;
import com.graves.meetingfilm.user.service.UserServiceAPI;
import com.graves.meetingfilm.utils.common.vo.BaseResponseVO;
import com.graves.meetingfilm.utils.exception.CommonServiceException;
import com.graves.meetingfilm.utils.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户模块展示层
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserServiceAPI userServiceAPI;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BaseResponseVO login(@RequestBody LoginReqVO reqVO) throws CommonServiceException {
        //数据验证
        reqVO.checkParam();

        //验证用户名密码是否正确
        String userId = userServiceAPI.checkUserLogin(reqVO.getUsername(), reqVO.getPassword());

        JwtTokenUtil tokenUtil = new JwtTokenUtil();
        String randomKey = tokenUtil.getRandomKey();
        String token = tokenUtil.generateToken(userId, randomKey);

        Map<String, String> result = new HashMap<>();
        result.put("randomKey", randomKey);
        result.put("token", token);
        return BaseResponseVO.success(result);
    }

}
