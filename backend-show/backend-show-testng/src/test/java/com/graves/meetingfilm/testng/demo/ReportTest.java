package com.graves.meetingfilm.testng.demo;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;


@Slf4j
public class ReportTest {

    @Test
    public void rt1(){
      log.error("ReportTest rt1");
    }

    @Test
    public void rt2(){
        int i = 6/0;
    }

}
