package com.graves.meetingfilm.film.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Maps;
import com.graves.meetingfilm.film.controller.vo.DescribeActorsRespVO;
import com.graves.meetingfilm.film.service.FilmServiceAPI;
import com.graves.meetingfilm.utils.common.vo.BasePageVO;
import com.graves.meetingfilm.utils.common.vo.BaseResponseVO;
import com.graves.meetingfilm.utils.exception.CommonServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Graves
 * @title: FilmController
 * @projectName backend-parent
 * @description: 影片展示模块
 * @date 2020/2/13 22:28
 */
@RestController
@RequestMapping(value = "/films")
public class FilmController {

    @Autowired
    private FilmServiceAPI filmServiceAPI;

    // 获取演员列表
    @RequestMapping(value = "/actors", method = RequestMethod.GET)
    public BaseResponseVO describeActors(BasePageVO basePageVO) throws CommonServiceException {
        // 参数校验
        basePageVO.checkParam();

        // 调用逻辑层，获取返回参数
        IPage<DescribeActorsRespVO> result = filmServiceAPI.describeActors(basePageVO.getNowPage(), basePageVO.getPageSize());

        Map<String, Object> actors = describePageResult(result, "actors");

        return BaseResponseVO.success(actors);
    }

    // 获取分页对象得公共接口
    private Map<String, Object> describePageResult(IPage page, String title) {
        Map<String, Object> result = Maps.newHashMap();
        result.put("totalSize", page.getTotal());
        result.put("totalPages", page.getPages());
        result.put("pageSize", page.getSize());
        result.put("nowPage", page.getCurrent());
        result.put(title, page.getRecords());
        return result;
    }
}
