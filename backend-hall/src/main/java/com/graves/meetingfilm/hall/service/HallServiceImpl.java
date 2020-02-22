package com.graves.meetingfilm.hall.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graves.meetingfilm.hall.controller.vo.HallSavedReqVO;
import com.graves.meetingfilm.hall.controller.vo.HallsReqVO;
import com.graves.meetingfilm.hall.controller.vo.HallsRespVO;
import com.graves.meetingfilm.hall.dao.mapper.MoocFieldTMapper;
import com.graves.meetingfilm.utils.exception.CommonServiceException;
import com.graves.meetingfilm.utils.util.ToolUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * @author Graves
 * @title: HallServiceImpl
 * @projectName backend-parent
 * @description: TODO
 * @date 2020/2/19 23:15
 */
public class HallServiceImpl implements HallServiceAPI {

    @Resource
    private MoocFieldTMapper fieldTMapper;

    /**
    * @Author Graves
    * @Description 查询影厅详情
    * @Date  21:51
    * @Param [hallsReqVO]
    * @return com.baomidou.mybatisplus.core.metadata.IPage<com.graves.meetingfilm.hall.controller.vo.HallsRespVO>
    */
    @Override
    public IPage<HallsRespVO> describeHalls(HallsReqVO hallsReqVO) throws CommonServiceException {
        Page<HallsReqVO> page = new Page<>(hallsReqVO.getNowPage(),hallsReqVO.getPageSize());

        QueryWrapper queryWrapper = new QueryWrapper();
        if(ToolUtils.strIsNotNull(hallsReqVO.getCinemaId())){
            queryWrapper.eq("cinema_id", hallsReqVO.getCinemaId());
        }

        IPage<HallsRespVO> result = fieldTMapper.describeHalls(page, queryWrapper);

        return result;
    }

    @Override
    public void saveHall(HallSavedReqVO hallSavedReqVO) throws CommonServiceException {

    }
}
