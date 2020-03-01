package com.graves.hystrix.show.command;

import org.checkerframework.checker.units.qual.C;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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
        long beginTime = System.currentTimeMillis();

        CommandDemo commandDemo = new CommandDemo("execute");

        //同步执行command
        String result = commandDemo.execute();

        long endTime = System.currentTimeMillis();
        System.out.println("result=" + result + " , speeding=" + (endTime - beginTime));
    }

    @Test
    public void queueTest() throws ExecutionException, InterruptedException {
        long beginTime = System.currentTimeMillis();

        CommandDemo commandDemo = new CommandDemo("queue");

        Future<String> queue = commandDemo.queue();

        long endTime = System.currentTimeMillis();

        System.out.println("future end , speeding="+(endTime-beginTime));

        long endTime2 = System.currentTimeMillis();

        System.out.println("result="+queue.get()+" , speeding="+(endTime2-beginTime));

    }
}
