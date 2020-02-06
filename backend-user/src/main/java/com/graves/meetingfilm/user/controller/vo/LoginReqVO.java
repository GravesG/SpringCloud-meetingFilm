package com.graves.meetingfilm.user.controller.vo;

import com.graves.meetingfilm.utils.common.vo.BaseRequestVO;
import com.graves.meetingfilm.utils.exception.CommonServiceException;
import com.graves.meetingfilm.utils.util.ToolUtils;
import lombok.Data;

/**
* @Author Graves
* @Description 登陆请求对象
* @Date  21:16
* @Param
* @return
*/
@Data
public class LoginReqVO extends BaseRequestVO {

    private String username;
    private String password;

    @Override
    public void checkParam() throws CommonServiceException {
        // TODO 验证数据合法性
        if(ToolUtils.strIsNull(username) || ToolUtils.strIsNull(password)){
            throw new CommonServiceException(404,"username 或 password 不能为空");
        }
    }
}
