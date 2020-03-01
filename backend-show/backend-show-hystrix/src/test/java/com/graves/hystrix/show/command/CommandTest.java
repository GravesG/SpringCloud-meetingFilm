package com.graves.hystrix.show.command;

import org.checkerframework.checker.units.qual.C;
import org.junit.Test;

/**
 * @author Graves
 * @title: CommandTest
 * @projectName backend-parent
 * @description: TODO
 * @date 2020/3/1 22:26
 */
public class CommandTest {
    @Test
    public void executeTest() {
        CommandDemo commandDemo = new CommandDemo("execute");

        //同步执行command
        String result = commandDemo.execute();

        System.out.println("result=" + result);
    }
}
