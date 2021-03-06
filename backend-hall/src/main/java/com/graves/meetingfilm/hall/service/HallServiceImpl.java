package com.graves.meetingfilm.hall.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graves.meetingfilm.apis.film.vo.DescribeFilmRespVO;
import com.graves.meetingfilm.hall.apis.FilmFeignApi;
import com.graves.meetingfilm.hall.controller.vo.HallSavedReqVO;
import com.graves.meetingfilm.hall.controller.vo.HallsReqVO;
import com.graves.meetingfilm.hall.controller.vo.HallsRespVO;
import com.graves.meetingfilm.hall.dao.entity.MoocFieldT;
import com.graves.meetingfilm.hall.dao.entity.MoocHallFilmInfoT;
import com.graves.meetingfilm.hall.dao.mapper.MoocFieldTMapper;
import com.graves.meetingfilm.hall.dao.mapper.MoocHallFilmInfoTMapper;
import com.graves.meetingfilm.utils.common.vo.BaseResponseVO;
import com.graves.meetingfilm.utils.exception.CommonServiceException;
import com.graves.meetingfilm.utils.util.ToolUtils;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author Graves
 * @title: HallServiceImpl
 * @projectName backend-parent
 * @description: TODO
 * @date 2020/2/19 23:15
 */
@Service
public class HallServiceImpl implements HallServiceAPI {

    @Resource
    private MoocFieldTMapper fieldTMapper;

    @Resource
    private MoocHallFilmInfoTMapper hallFilmInfoTMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient eurekaClient;

    @Resource
    private FilmFeignApi filmFeignApi;

    /**
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.graves.meetingfilm.hall.controller.vo.HallsRespVO>
     * @Author Graves
     * @Description 查询影厅详情
     * @Date 21:51
     * @Param [hallsReqVO]
     */
    @Override
    public IPage<HallsRespVO> describeHalls(HallsReqVO hallsReqVO) throws CommonServiceException {
        Page<HallsReqVO> page = new Page<>(hallsReqVO.getNowPage(), hallsReqVO.getPageSize());

        QueryWrapper queryWrapper = new QueryWrapper();
        if (ToolUtils.strIsNotNull(hallsReqVO.getCinemaId())) {
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
//    private MoocHallFilmInfoT describeFilmInfo(String filmId) throws CommonServiceException {
//        // GET REGISTER
//        ServiceInstance choose = eurekaClient.choose("film-service");
//        // 组织调用参数
//        String hostname = choose.getHost();
//        int port = choose.getPort();
//
//        String uri = "/films/" + filmId;
//
//        String url = "http://" + hostname + ":" + port + uri;
//
//        // 通过restTemplate调用影片服务
//        JSONObject baseResponseVO = restTemplate.getForObject(url, JSONObject.class);
//
//        // 解析返回值
//        JSONObject dataJson = baseResponseVO.getJSONObject("data");
//
//        // 组织参数
//        MoocHallFilmInfoT hallFilmInfo = new MoocHallFilmInfoT();
//
//        hallFilmInfo.setFilmId(dataJson.getIntValue("filmId"));
//        hallFilmInfo.setFilmName(dataJson.getString("filmName"));
//        hallFilmInfo.setFilmLength(dataJson.getString("filmLength"));
//        hallFilmInfo.setFilmCats(dataJson.getString("filmCats"));
//        hallFilmInfo.setActors(dataJson.getString("actors"));
//        hallFilmInfo.setImgAddress(dataJson.getString("imgAddress"));
//
//        return hallFilmInfo;
//    }

    // 播放厅对应的影片数据， 影片冗余数据， 缓存里有一份(跨服务调用)
    private MoocHallFilmInfoT describeFilmInfo(String filmId) throws CommonServiceException {
        BaseResponseVO<DescribeFilmRespVO> baseResponseVO = filmFeignApi.describeFilmById(filmId);
        DescribeFilmRespVO filmResult = baseResponseVO.getData();
        if (filmResult == null || ToolUtils.strIsNull(filmResult.getFilmId())) {
            throw new CommonServiceException(404, "抱歉，未能找到对应的电影信息，filmId:" + filmId);
        }
        // 组织参数
        MoocHallFilmInfoT hallFilmInfo = new MoocHallFilmInfoT();

        hallFilmInfo.setFilmId(ToolUtils.str2Int(filmResult.getFilmId()));
        hallFilmInfo.setFilmName(filmResult.getFilmName());
        hallFilmInfo.setFilmLength(filmResult.getFilmLength());
        hallFilmInfo.setFilmCats(filmResult.getFilmCats());
        hallFilmInfo.setActors(filmResult.getActors());
        hallFilmInfo.setImgAddress(filmResult.getImgAddress());

        return hallFilmInfo;
    }
}
