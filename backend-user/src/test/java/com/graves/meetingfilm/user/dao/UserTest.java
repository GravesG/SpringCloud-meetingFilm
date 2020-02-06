package com.graves.meetingfilm.user.dao;

import com.graves.meetingfilm.user.BackendUserApplicationTests;
import com.graves.meetingfilm.user.dao.entity.MoocBackendUserT;
import com.graves.meetingfilm.user.dao.mapper.MoocBackendUserTMapper;
import com.graves.meetingfilm.utils.util.MD5Util;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class UserTest extends BackendUserApplicationTests {

    @Resource
    private MoocBackendUserTMapper backendUser;

    /**
     * 加密密码
     */
    @Test
    public void add(){
        MoocBackendUserT user = new MoocBackendUserT();
        user.setUserName("admin");
        user.setUserPwd(MD5Util.encrypt("admin123"));
        user.setUserPhone("18599999999");

        backendUser.insert(user);
    }

    @Test
    public void select(){

        List<MoocBackendUserT> moocBackendUserTS = backendUser.selectList(null);
        moocBackendUserTS.stream().forEach(
                System.out::println
        );
    }

}

