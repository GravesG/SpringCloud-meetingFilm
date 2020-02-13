package com.graves.meetingfilm.film.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.graves.meetingfilm.film.controller.vo.DescribeActorsRespVO;
import com.graves.meetingfilm.film.controller.vo.DescribeFilmsRespVO;

/**
 * @author Graves
 * @title: FilmServiceAPI
 * @projectName backend-parent
 * @description: TODO
 * @date 2020/2/13 22:54
 */
public interface FilmServiceAPI {

    // 获取演员列表
    IPage<DescribeActorsRespVO> describeActors(int nowPage,int pageSize);

    // 获取电影列表
    IPage<DescribeFilmsRespVO> describeFilms(int nowPage, int pageSize);
}
