package com.graves.meetingfilm.cinema.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Maps;
import com.graves.meetingfilm.cinema.controller.vo.CinemaSavedReqVO;
import com.graves.meetingfilm.cinema.controller.vo.DescribeCinemasRespVO;
import com.graves.meetingfilm.cinema.service.CinemaServiceAPI;
import com.graves.meetingfilm.utils.common.vo.BasePageVO;
import com.graves.meetingfilm.utils.common.vo.BaseRequestVO;
import com.graves.meetingfilm.utils.common.vo.BaseResponseVO;
import com.graves.meetingfilm.utils.exception.CommonServiceException;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Graves
 * @title: CinemaController
 * @projectName backend-parent
 * @description: 影院controller
 * @date 2020/2/18 22:13
 */
@RestController
@RequestMapping("/cinemas")
public class CinemaController {

    @Autowired
    private CinemaServiceAPI cinemaServiceAPI;

    @RequestMapping(value = "/cinema:add",method = RequestMethod.POST)
    public BaseResponseVO saveCiname(@RequestBody CinemaSavedReqVO reqVO) throws CommonServiceException {
        // 参数校验
        reqVO.checkParam();

        cinemaServiceAPI.saveCinema(reqVO);

        return BaseResponseVO.success();
    }
    @RequestMapping(value = "",method = RequestMethod.GET)
    public BaseResponseVO describeCinemas(BasePageVO pageVO) throws CommonServiceException {

        IPage<DescribeCinemasRespVO> describeCinemasRespVOIPage = cinemaServiceAPI.describeCinemas(pageVO.getNowPage(), pageVO.getPageSize());

        // 调用封装的分页返回方法
        Map<String, Object> map = describePageResult(describeCinemasRespVOIPage, "cinemas");

        return BaseResponseVO.success(map);
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
