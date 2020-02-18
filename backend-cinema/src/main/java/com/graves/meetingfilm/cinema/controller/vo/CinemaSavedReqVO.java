package com.graves.meetingfilm.cinema.controller.vo;


import com.graves.meetingfilm.utils.common.vo.BaseRequestVO;
import com.graves.meetingfilm.utils.exception.CommonServiceException;
import lombok.Data;

@Data
public class CinemaSavedReqVO extends BaseRequestVO {

    private String brandId;
    private String areaId;
    private String hallTypeIds;
    private String cinemaName;
    private String cinemaAddress;
    private String cinemaTele;
    private String cinemaImgAddress;
    private String cinemaPrice;

    @Override
    public void checkParam() throws CommonServiceException {

    }
}
