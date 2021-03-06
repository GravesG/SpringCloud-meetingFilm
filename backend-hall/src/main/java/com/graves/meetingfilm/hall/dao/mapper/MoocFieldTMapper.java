package com.graves.meetingfilm.hall.dao.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graves.meetingfilm.hall.controller.vo.HallsReqVO;
import com.graves.meetingfilm.hall.controller.vo.HallsRespVO;
import com.graves.meetingfilm.hall.dao.entity.MoocFieldT;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 放映场次表 Mapper 接口
 * </p>
 *
 * @author graves
 * @since 2020-02-18
 */
public interface MoocFieldTMapper extends BaseMapper<MoocFieldT> {

    IPage<HallsRespVO> describeHalls(Page<HallsReqVO> page, @Param("ew") QueryWrapper queryWrapper);
}
