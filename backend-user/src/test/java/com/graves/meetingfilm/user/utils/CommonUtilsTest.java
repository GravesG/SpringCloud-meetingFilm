package com.graves.meetingfilm.user.utils;

import com.graves.meetingfilm.utils.common.vo.BaseResponseVo;
import org.junit.Test;

public class CommonUtilsTest {
    @Test
    public void test(){
        BaseResponseVo responseVo = new BaseResponseVo();
        System.out.println(responseVo.run("user"));
    }
}
