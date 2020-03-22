package com.graves.meetingfilm.utils.common.vo;

import com.graves.meetingfilm.utils.exception.CommonServiceException;
import lombok.Data;

/**
 * 表现层公共返回
 */
@Data
public class BaseResponseVO<M> {

    private Integer code;
    private String message;
    private M data;

    private BaseResponseVO(){}

    // 成功但是无参数
    public static BaseResponseVO success(){
        BaseResponseVO response = new BaseResponseVO();
        response.setCode(200);
        response.setMessage("");
        return response;
    }

    // 成功有参数
    public static<M> BaseResponseVO success(M data){
        BaseResponseVO response = new BaseResponseVO();
        response.setCode(200);
        response.setMessage("");
        response.setData(data);
        return response;
    }

    // 出现业务异常
    public static<M> BaseResponseVO serviceException(CommonServiceException e){
        BaseResponseVO response = new BaseResponseVO();
        response.setCode(e.getCode());
        response.setMessage(e.getMessage());
        return response;
    }

    // 未登录异常
    public static<M> BaseResponseVO noLogin(){
        BaseResponseVO response = new BaseResponseVO();
        response.setCode(401);
        response.setMessage("请登录");
        return response;
    }
}
