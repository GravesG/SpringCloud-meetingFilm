package com.graves.meetingfilm.film.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graves.meetingfilm.film.controller.vo.DescribeActorsRespVO;
import com.graves.meetingfilm.film.controller.vo.DescribeFilmRespVO;
import com.graves.meetingfilm.film.controller.vo.DescribeFilmsRespVO;
import com.graves.meetingfilm.film.controller.vo.FilmSavedReqVO;
import com.graves.meetingfilm.film.dao.mapper.MoocActorTMapper;
import com.graves.meetingfilm.film.dao.mapper.MoocFilmActorTMapper;
import com.graves.meetingfilm.film.dao.mapper.MoocFilmInfoTMapper;
import com.graves.meetingfilm.film.dao.mapper.MoocFilmTMapper;
import com.graves.meetingfilm.utils.exception.CommonServiceException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Graves
 * @title: FilmServiceImpl
 * @projectName backend-parent
 * @description: TODO
 * @date 2020/2/13 22:54
 */
@Service
public class FilmServiceImpl implements FilmServiceAPI {

    @Resource
    private MoocActorTMapper moocActorTMapper;
    @Resource
    private MoocFilmActorTMapper moocFilmActorTMapper;
    @Resource
    private MoocFilmInfoTMapper moocFilmInfoTMapper;
    @Resource
    private MoocFilmTMapper moocFilmTMapper;

    @Override
    public IPage<DescribeActorsRespVO> describeActors(int nowPage, int pageSize) throws CommonServiceException{
        return moocActorTMapper.describeActors(new Page<>(nowPage,pageSize));
    }

    @Override
    public IPage<DescribeFilmsRespVO> describeFilms(int nowPage, int pageSize) {
        return moocFilmTMapper.describeFilms(new Page<>(nowPage,pageSize));
    }

    @Override
    public DescribeFilmRespVO describeFilmById(String filmId) throws CommonServiceException {
        return null;
    }

    @Override
    public void save(FilmSavedReqVO filmSavedReqVO) throws CommonServiceException {

    }
}
