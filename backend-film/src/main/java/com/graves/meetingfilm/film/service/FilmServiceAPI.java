package com.graves.meetingfilm.film.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.graves.meetingfilm.film.controller.vo.DescribeActorsRespVO;
import com.graves.meetingfilm.film.controller.vo.DescribeFilmRespVO;
import com.graves.meetingfilm.film.controller.vo.DescribeFilmsRespVO;
import com.graves.meetingfilm.film.controller.vo.FilmSavedReqVO;
import com.graves.meetingfilm.utils.exception.CommonServiceException;

/**
 * @author Graves
 * @title: FilmServiceAPI
 * @projectName backend-parent
 * @description: TODO
 * @date 2020/2/13 22:54
 */
public interface FilmServiceAPI {

    // 获取演员列表
    IPage<DescribeActorsRespVO> describeActors(int nowPage,int pageSize) throws CommonServiceException;

    // 获取电影列表
    IPage<DescribeFilmsRespVO> describeFilms(int nowPage, int pageSize) throws CommonServiceException;

    // 根据id获取电影
    DescribeFilmRespVO describeFilmById(String filmId) throws CommonServiceException;

    void save(FilmSavedReqVO filmSavedReqVO) throws CommonServiceException;
}
