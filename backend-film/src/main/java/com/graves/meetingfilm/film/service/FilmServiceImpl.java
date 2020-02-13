package com.graves.meetingfilm.film.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.graves.meetingfilm.film.controller.vo.DescribeActorsRespVO;
import org.springframework.stereotype.Service;

/**
 * @author Graves
 * @title: FilmServiceImpl
 * @projectName backend-parent
 * @description: TODO
 * @date 2020/2/13 22:54
 */
@Service
public class FilmServiceImpl implements FilmServiceAPI {
    @Override
    public IPage<DescribeActorsRespVO> describeActors(int nowPage, int pageSize) {
        return null;
    }
}
