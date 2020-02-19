package com.graves.meetingfilm.hall.controller.vo;


import com.graves.meetingfilm.utils.common.vo.BaseRequestVO;
import com.graves.meetingfilm.utils.exception.CommonServiceException;
import lombok.Data;

/**
 * @author : graves
 * @program : com.graves.meetingfilm.hall.controller.vo
 * @description :
 **/
@Data
public class HallSavedReqVO extends BaseRequestVO {

    private String cinemaId;
    private String filmId;
    private String hallTypeId;
    private String beginTime;
    private String endTime;
    private String filmPrice;
    private String hallName;

    @Override
    public void checkParam() throws CommonServiceException {

    }
}
