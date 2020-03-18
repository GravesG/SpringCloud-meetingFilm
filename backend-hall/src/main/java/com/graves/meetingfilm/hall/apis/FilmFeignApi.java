package com.graves.meetingfilm.hall.apis;

import com.graves.meetingfilm.apis.film.FilmFeignApis;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Graves
 * @title: FilmFeignApi
 * @projectName backend-parent
 * @description: film提供的接口
 * @date 2020/3/18 21:52
 */
@FeignClient(name = "film-service")
public interface FilmFeignApi extends FilmFeignApis {
}
