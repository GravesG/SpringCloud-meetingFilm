package com.graves.meetingfilm.backend.common.user;

import com.graves.meetingfilm.backend.common.BackendCommonApplicationTests;
import com.graves.meetingfilm.backend.common.dao.entity.MoocBackendUserT;
import com.graves.meetingfilm.backend.common.dao.mapper.MoocBackendUserTMapper;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class UserTest extends BackendCommonApplicationTests {

    @Resource
    private MoocBackendUserTMapper backendUser;

    @Test
    public void add(){
        MoocBackendUserT user = new MoocBackendUserT();
        user.setUserName("gzt");
        user.setUserPwd("gzt");
        user.setUserPhone("123");
        backendUser.insert(user);
    }

    @Test
    public void select(){
        /*MoocBackendUserT moocBackendUserT = backendUser.selectById(2);
        System.out.println(moocBackendUserT);*/

        List<MoocBackendUserT> moocBackendUserTS = backendUser.selectList(null);
        moocBackendUserTS.stream().forEach(
                System.out::println
        );
    }

    @Test
    public void update(){
        MoocBackendUserT user = new MoocBackendUserT();
        user.setUuid(2);
        user.setUserName("123");
        user.setUserPwd("123");
        user.setUserPhone("123");
        backendUser.updateById(user);

    }

    @Test
    public void del(){
        backendUser.deleteById(2);
    }
}

