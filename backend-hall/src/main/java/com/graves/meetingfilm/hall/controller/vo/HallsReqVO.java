package com.graves.meetingfilm.hall.controller.vo;


import com.graves.meetingfilm.utils.common.vo.BasePageVO;
import com.graves.meetingfilm.utils.exception.CommonServiceException;
import lombok.Data;

/**
 * @author : Graves
 * @program : com.graves.meetingfilm.hall.controller.vo
 * @description :
 **/
@Data
public class HallsReqVO extends BasePageVO {

    private String cinemaId;

    @Override
    public void checkParam() throws CommonServiceException {
        super.checkParam();
    }
}
