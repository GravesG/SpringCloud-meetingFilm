package com.graves.meetingfilm.apis.film;

import com.graves.meetingfilm.apis.film.vo.DescribeFilmRespVO;
import com.graves.meetingfilm.utils.common.vo.BaseResponseVO;
import com.graves.meetingfilm.utils.exception.CommonServiceException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Graves
 * @title: FilmFeignApis
 * @projectName backend-parent
 * @description: TODO
 * @date 2020/3/18 21:42
 */
public interface FilmFeignApis {

    /**
     * 对外暴露的接口服务
     *
     * @param filmId
     * @return
     * @throws CommonServiceException
     */
    @RequestMapping(value = "/films/{filmId}", method = RequestMethod.GET)
    BaseResponseVO<DescribeFilmRespVO> describeFilmById(@PathVariable("filmId") String filmId) throws CommonServiceException;
}
