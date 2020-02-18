package com.graves.meetingfilm.cinema.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.graves.meetingfilm.cinema.controller.vo.CinemaSavedReqVO;
import com.graves.meetingfilm.cinema.controller.vo.DescribeCinemasRespVO;
import com.graves.meetingfilm.utils.exception.CommonServiceException;


/**
 * @author : jiangzh
 * @program : com.mooc.meetingfilm.cinema.service
 * @description :
 **/
public interface CinemaServiceAPI {

    // 保存影院
    void saveCinema(CinemaSavedReqVO reqVO) throws CommonServiceException;

    // 获取影院列表
    IPage<DescribeCinemasRespVO> describeCinemas(int nowPage, int pageSize) throws CommonServiceException;

}
