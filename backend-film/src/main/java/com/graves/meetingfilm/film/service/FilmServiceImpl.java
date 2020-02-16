package com.graves.meetingfilm.film.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graves.meetingfilm.film.controller.vo.DescribeActorsRespVO;
import com.graves.meetingfilm.film.controller.vo.DescribeFilmRespVO;
import com.graves.meetingfilm.film.controller.vo.DescribeFilmsRespVO;
import com.graves.meetingfilm.film.controller.vo.FilmSavedReqVO;
import com.graves.meetingfilm.film.dao.entity.MoocFilmActorT;
import com.graves.meetingfilm.film.dao.entity.MoocFilmInfoT;
import com.graves.meetingfilm.film.dao.entity.MoocFilmT;
import com.graves.meetingfilm.film.dao.mapper.MoocActorTMapper;
import com.graves.meetingfilm.film.dao.mapper.MoocFilmActorTMapper;
import com.graves.meetingfilm.film.dao.mapper.MoocFilmInfoTMapper;
import com.graves.meetingfilm.film.dao.mapper.MoocFilmTMapper;
import com.graves.meetingfilm.utils.exception.CommonServiceException;
import com.graves.meetingfilm.utils.util.ToolUtils;
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
        return moocFilmTMapper.descrbeFilmById(filmId);
    }

    @Override
    public void save(FilmSavedReqVO reqVO) throws CommonServiceException {
        try{
            // 保存电影主表
            MoocFilmT film = new MoocFilmT();
            film.setFilmName(reqVO.getFilmName());
            film.setFilmType(ToolUtils.str2Int(reqVO.getFilmTypeId()));
            film.setImgAddress(reqVO.getMainImgAddress());
            film.setFilmScore(reqVO.getFilmScore());
            film.setFilmPresalenum(ToolUtils.str2Int(reqVO.getPreSaleNum()));
            film.setFilmBoxOffice(ToolUtils.str2Int(reqVO.getBoxOffice()));
            film.setFilmSource(ToolUtils.str2Int(reqVO.getFilmSourceId()));
            film.setFilmCats(reqVO.getFilmCatIds());
            film.setFilmArea(ToolUtils.str2Int(reqVO.getAreaId()));
            film.setFilmDate(ToolUtils.str2Int(reqVO.getDateId()));
            film.setFilmTime(ToolUtils.str2LocalDateTime(reqVO.getFilmTime()+" 00:00:00"));
            film.setFilmStatus(ToolUtils.str2Int(reqVO.getFilmStatus()));

            moocFilmTMapper.insert(film);
            // 保存电影子表
            MoocFilmInfoT filmInfo = new MoocFilmInfoT();

            filmInfo.setFilmId(film.getUuid()+"");
            filmInfo.setFilmEnName(reqVO.getFilmEnName());
            filmInfo.setFilmScore(reqVO.getFilmScore());
            filmInfo.setFilmScoreNum(ToolUtils.str2Int(reqVO.getFilmScorers()));
            filmInfo.setFilmLength(ToolUtils.str2Int(reqVO.getFilmLength()));
            filmInfo.setBiography(reqVO.getBiography());
            filmInfo.setDirectorId(ToolUtils.str2Int(reqVO.getDirectorId()));
            filmInfo.setFilmImgs(reqVO.getFilmImgs());


            moocFilmInfoTMapper.insert(filmInfo);

            String[] actorId = reqVO.getActIds().split("#");
            String[] roleNames = reqVO.getRoleNames().split("#");
            if(actorId.length != roleNames.length){
                throw new CommonServiceException(500, "演员和角色名数量不匹配");
            }

            for(int i=0;i<actorId.length;i++){
                // 保存演员映射表
                MoocFilmActorT filmActor = new MoocFilmActorT();

                filmActor.setFilmId(film.getUuid());
                filmActor.setActorId(ToolUtils.str2Int(actorId[i]));
                filmActor.setRoleName(roleNames[i]);

                moocFilmActorTMapper.insert(filmActor);
            }
        }catch (Exception e){
            throw new CommonServiceException(500, e.getMessage());
        }
    }
}
