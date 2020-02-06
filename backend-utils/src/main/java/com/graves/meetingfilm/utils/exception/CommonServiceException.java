package com.graves.meetingfilm.utils.exception;

import lombok.Data;

/**
 * @author Graves
 * @title: CommonServiceException
 * @projectName backend-parent
 * @description: TODO
 * @date 2020/2/621:06
 */
@Data
public class CommonServiceException extends Exception {

    private Integer code;
    private String message;

    public CommonServiceException(int code,String message){
        this.code = code;
        this.message = message;
    }
}
