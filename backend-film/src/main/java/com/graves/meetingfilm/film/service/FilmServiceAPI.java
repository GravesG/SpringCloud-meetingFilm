package com.graves.meetingfilm.film.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.graves.meetingfilm.film.controller.vo.DescribeActorsRespVO;

/**
 * @author Graves
 * @title: FilmServiceAPI
 * @projectName backend-parent
 * @description: TODO
 * @date 2020/2/13 22:54
 */
public interface FilmServiceAPI {

    IPage<DescribeActorsRespVO> describeActors(int nowPage,int pageSize);
}
