package com.graves.meetingfilm.cinema.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.graves.meetingfilm.cinema.controller.vo.CinemaSavedReqVO;
import com.graves.meetingfilm.cinema.controller.vo.DescribeCinemasRespVO;
import com.graves.meetingfilm.cinema.dao.entity.MoocCinemaT;
import com.graves.meetingfilm.cinema.dao.mapper.MoocCinemaTMapper;
import com.graves.meetingfilm.utils.exception.CommonServiceException;
import com.graves.meetingfilm.utils.util.ToolUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Graves
 * @program : com.graves.meetingfilm.cinema.service
 * @description :impl
 **/
@Service
public class CinemaServiceImpl implements CinemaServiceAPI{

    @Resource
    private MoocCinemaTMapper cinemaTMapper;

    @Override
    public void saveCinema(CinemaSavedReqVO reqVO) throws CommonServiceException {
        MoocCinemaT cinema = new MoocCinemaT();

        // TODO 填写具体参数
        cinema.setCinemaName(reqVO.getCinemaName());
        cinema.setCinemaPhone(reqVO.getCinemaTele());
        cinema.setBrandId(ToolUtils.str2Int(reqVO.getBrandId()));
        cinema.setAreaId(ToolUtils.str2Int(reqVO.getAreaId()));
        cinema.setHallIds(reqVO.getHallTypeIds());
        cinema.setImgAddress(reqVO.getCinemaImgAddress());
        cinema.setCinemaAddress(reqVO.getCinemaAddress());
        cinema.setMinimumPrice(ToolUtils.str2Int(reqVO.getCinemaPrice()));

        // TODO 记得把实体对象进行保存
        cinemaTMapper.insert(cinema);
    }


    @Override
    public IPage<DescribeCinemasRespVO> describeCinemas(int nowPage, int pageSize) throws CommonServiceException {

        // 查询实体对象，然后与表现层对象进行交互
        // TODO 提示
        Page<MoocCinemaT> page = new Page<>(nowPage,pageSize);
        IPage<MoocCinemaT> moocCinemaTIPage = cinemaTMapper.selectPage(page, null);

        // moocCinemaTIPage对象内的分页参数与IPage<DescribeCinemasRespVO>一模一样

        List<MoocCinemaT> records = moocCinemaTIPage.getRecords();// set到IPage<DescribeCinemasRespVO> records方法中
        List<DescribeCinemasRespVO> result = new ArrayList<>();
        for(MoocCinemaT moocCinemaT : records){
            DescribeCinemasRespVO respVO = new DescribeCinemasRespVO();
            respVO.setAreaId(moocCinemaT.getAreaId()+"");
            respVO.setBrandId(moocCinemaT.getBrandId()+"");
            respVO.setHallTypeIds(moocCinemaT.getHallIds());
            respVO.setCinemaAddress(moocCinemaT.getCinemaAddress());
            respVO.setCinemaImgAddress(moocCinemaT.getImgAddress());
            respVO.setCinemaName(moocCinemaT.getCinemaName());
            respVO.setCinemaPrice(moocCinemaT.getMinimumPrice()+"");
            respVO.setCinemaTele(moocCinemaT.getCinemaPhone());
            result.add(respVO);
        }

        Page<DescribeCinemasRespVO> res = new Page<>();
        res.setTotal(moocCinemaTIPage.getTotal());
        res.setCurrent(moocCinemaTIPage.getCurrent());
        res.setPages(moocCinemaTIPage.getPages());
        res.setRecords(result);

        return res;
    }
}
