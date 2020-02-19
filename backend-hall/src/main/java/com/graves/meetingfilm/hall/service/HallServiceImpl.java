package com.graves.meetingfilm.hall.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.graves.meetingfilm.hall.controller.vo.HallSavedReqVO;
import com.graves.meetingfilm.hall.controller.vo.HallsReqVO;
import com.graves.meetingfilm.hall.controller.vo.HallsRespVO;
import com.graves.meetingfilm.utils.exception.CommonServiceException;

/**
 * @author Graves
 * @title: HallServiceImpl
 * @projectName backend-parent
 * @description: TODO
 * @date 2020/2/19 23:15
 */
public class HallServiceImpl implements HallServiceAPI {
    @Override
    public IPage<HallsRespVO> describeHalls(HallsReqVO hallsReqVO) throws CommonServiceException {
        return null;
    }

    @Override
    public void saveHall(HallSavedReqVO hallSavedReqVO) throws CommonServiceException {

    }
}
