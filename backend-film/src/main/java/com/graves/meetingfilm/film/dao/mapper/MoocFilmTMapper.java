package com.graves.meetingfilm.film.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graves.meetingfilm.apis.film.vo.DescribeFilmRespVO;
import com.graves.meetingfilm.film.controller.vo.DescribeFilmsRespVO;
import com.graves.meetingfilm.film.dao.entity.MoocFilmT;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 影片主表 Mapper 接口
 * </p>
 *
 * @author graves
 * @since 2020-02-13
 */
public interface MoocFilmTMapper extends BaseMapper<MoocFilmT> {

    IPage<DescribeFilmsRespVO> describeFilms(Page<DescribeFilmsRespVO> page );

    DescribeFilmRespVO descrbeFilmById(@Param("filmId") String filmId);
}
