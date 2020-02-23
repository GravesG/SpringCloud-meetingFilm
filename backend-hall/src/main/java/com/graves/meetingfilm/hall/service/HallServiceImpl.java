package com.graves.meetingfilm.hall.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graves.meetingfilm.hall.controller.vo.HallSavedReqVO;
import com.graves.meetingfilm.hall.controller.vo.HallsReqVO;
import com.graves.meetingfilm.hall.controller.vo.HallsRespVO;
import com.graves.meetingfilm.hall.dao.entity.MoocFieldT;
import com.graves.meetingfilm.hall.dao.entity.MoocHallFilmInfoT;
import com.graves.meetingfilm.hall.dao.mapper.MoocFieldTMapper;
import com.graves.meetingfilm.hall.dao.mapper.MoocHallFilmInfoTMapper;
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

    @Resource
    private MoocHallFilmInfoTMapper hallFilmInfoTMapper;

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
    public void saveHall(HallSavedReqVO reqVO) throws CommonServiceException {
// 播放厅的列表数据
        MoocFieldT field = new MoocFieldT();

        field.setCinemaId(ToolUtils.str2Int(reqVO.getCinemaId()));
        field.setFilmId(ToolUtils.str2Int(reqVO.getFilmId()));
        field.setBeginTime(reqVO.getBeginTime());
        field.setEndTime(reqVO.getEndTime());
        field.setHallId(ToolUtils.str2Int(reqVO.getHallTypeId()));
        field.setHallName(reqVO.getHallName());
        field.setPrice(ToolUtils.str2Int(reqVO.getFilmPrice()));

        fieldTMapper.insert(field);
        // 播放厅对应的影片数据， 影片冗余数据， 缓存里有一份
        MoocHallFilmInfoT hallFilmInfo = describeFilmInfo(reqVO.getFilmId());

        hallFilmInfoTMapper.insert(hallFilmInfo);
    }

    // 播放厅对应的影片数据， 影片冗余数据， 缓存里有一份
    private MoocHallFilmInfoT describeFilmInfo(String filmId) throws CommonServiceException{
        return null;
    }
}
