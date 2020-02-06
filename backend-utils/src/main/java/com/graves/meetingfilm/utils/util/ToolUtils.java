package com.graves.meetingfilm.utils.util;

/**
 * @author Graves
 * @title: ToolUtils
 * @projectName backend-parent
 * @description: 基础工具类
 * @date 2020/2/6 21:10
 */
public class ToolUtils {

    private ToolUtils(){}

    /**
    * @Author Graves
    * @Description 判断字符串为空
    * @Date  21:12
    * @Param [str]
    * @return boolean
    */
    public static boolean strIsNull(String str){
        if(str == null || str.trim().length() == 0){
            return true;
        }else {
            return false;
        }
    }

    /**
    * @Author Graves
    * @Description 判断字符串不为空
    * @Date  21:13
    * @Param [str]
    * @return boolean
    */
    public static boolean strIsNotNull(String str){
        if(strIsNull(str)){
            return false;
        }else {
            return true;
        }
    }
}
