package com.graves.meetingfilm.hall.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.graves.meetingfilm.hall.controller.vo.HallSavedReqVO;
import com.graves.meetingfilm.hall.controller.vo.HallsReqVO;
import com.graves.meetingfilm.hall.controller.vo.HallsRespVO;
import com.graves.meetingfilm.utils.exception.CommonServiceException;


/**
 * @author : graves
 * @program : com.graves.meetingfilm.hall.service
 * @description :
 **/
public interface HallServiceAPI {

    // 获取全部播放厅接口
    IPage<HallsRespVO> describeHalls(HallsReqVO hallsReqVO) throws CommonServiceException;

    // 保存影厅信息
    void saveHall(HallSavedReqVO hallSavedReqVO) throws CommonServiceException;

}
