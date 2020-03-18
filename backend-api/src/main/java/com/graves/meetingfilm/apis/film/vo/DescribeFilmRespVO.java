package com.graves.meetingfilm.apis.film.vo;

import lombok.Data;

/**
 * @author Graves
 * @title: DescribeFilmRespVO
 * @projectName backend-parent
 * @description: 根据id获取电影
 * @date 2020/2/14 21:43
 */
@Data
public class DescribeFilmRespVO {

     private String filmId;
     private String filmName;
     private String filmLength;
     private String filmCats;
     private String actors;
     private String imgAddress;
     private String subAddress;
}
