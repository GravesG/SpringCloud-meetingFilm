package com.graves.meetingfilm.film.controller.vo;

import lombok.Data;

/**
 * @author Graves
 * @title: DescribeFilmsRespVO
 * @projectName backend-parent
 * @description: TODO
 * @date 2020/2/13 23:03
 */
@Data
public class DescribeFilmsRespVO {

    private String filmId;
    private String filmStatus;
    private String filmName;
    private String filmEnName;
    private String filmScore;
    private String preSaleNum;
    private String boxOffice;
    private String filmTime;
    private String filmLength;
    private String mainImg;
}
