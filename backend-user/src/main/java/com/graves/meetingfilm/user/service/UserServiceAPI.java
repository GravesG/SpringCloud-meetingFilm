package com.graves.meetingfilm.user.service;

import com.graves.meetingfilm.utils.exception.CommonServiceException;

/**
 * @author Graves
 * @title: UserServiceAPI
 * @projectName backend-parent
 * @description: 用户接口
 * @date 2020/2/6 23:01
 */
public interface UserServiceAPI {

    String checkUserLogin(String username, String password) throws CommonServiceException;
}
