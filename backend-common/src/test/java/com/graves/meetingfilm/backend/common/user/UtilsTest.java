package com.graves.meetingfilm.backend.common.user;

import com.graves.meetingfilm.utils.common.vo.BaseResponseVo;
import org.junit.Test;

public class UtilsTest {

    @Test
    public void test(){
        BaseResponseVo baseResponseVo = new BaseResponseVo();
        System.out.println(baseResponseVo.run("hello "));
    }
}
