package com.graves.meetingfilm.utils.common.vo;

import com.graves.meetingfilm.utils.exception.CommonServiceException;
import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * @author Graves
 * @title: BasePageVO
 * @projectName backend-parent
 * @description: TODO
 * @date 2020/2/13 22:30
 */
@Data
public class BasePageVO extends BaseRequestVO {

    private Integer nowPage = 1;
    private Integer pageSize = 10;

    @Override
    public void checkParam() throws CommonServiceException {
        if (nowPage == null || pageSize == null) {
            throw new CommonServiceException(500, "页面参数错误");
        }
    }
}
