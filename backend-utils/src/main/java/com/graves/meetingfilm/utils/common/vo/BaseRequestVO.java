package com.graves.meetingfilm.utils.common.vo;


import com.graves.meetingfilm.utils.exception.CommonServiceException;

public abstract class BaseRequestVO {

    /**
    * @Author Graves
    * @Description //公共参数校验方法
    * @Date  21:02
    * @Param []
    * @return void
    */
    public abstract void checkParam() throws CommonServiceException;

}
