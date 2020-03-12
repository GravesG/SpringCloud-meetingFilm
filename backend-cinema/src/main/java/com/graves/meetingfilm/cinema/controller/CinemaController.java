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
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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

    public BaseResponseVO BaseResponseVO(BasePageVO pageVO) throws CommonServiceException{
        /*
            打发票， -》 没打印纸了， 换台机器或者下次再试
            -》 触发告警 -》 告知运维人员，打印发票业务挂了
         */
        // describeCinemas -》 获取影院列表 -> 在Redis中查询影院列表[redis挂了，或者数据没了] -> 获取超时

        // fallback里捕获到超时或者数据内容与分页数不一致

        // fallback就在数据库中查询真实的影院信息

        // 返回一定是成功，或者业务处理失败
        Map<String,String> result = Maps.newHashMap();
        result.put("code","500");
        result.put("message","请求处理降级返回");
        return BaseResponseVO.success(result);
    }

    @HystrixCommand(fallbackMethod = "BaseResponseVO",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value= "1000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50")
            },
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "1"),
                    @HystrixProperty(name = "maxQueueSize", value = "10"),
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "1000"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "8"),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1500")
            },ignoreExceptions = CommonServiceException.class)
    @RequestMapping(value = "",method = RequestMethod.GET)
    public BaseResponseVO describeCinemas(BasePageVO pageVO) throws CommonServiceException {

        IPage<DescribeCinemasRespVO> describeCinemasRespVOIPage = cinemaServiceAPI.describeCinemas(pageVO.getNowPage(), pageVO.getPageSize());

        if(pageVO.getNowPage() > 10000){
            throw new CommonServiceException(400,"nowPage太大了，不支持此操作");
//            try {
//                Thread.sleep(2000l);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }

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
