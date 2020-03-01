package com.graves.hystrix.show.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import lombok.Data;

/**
 * @author Graves
 * @title: CommandDemo
 * @projectName backend-parent
 * @description: TODO
 * @date 2020/3/1 22:18
 */
@Data
public class CommandDemo extends HystrixCommand<String> {

    private String name;

    public CommandDemo(String name) {
        super(Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("CommandHelloWorld")));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        String result = "CommandHelloWorld name:" + name;

        System.err.println(result + "currentThread-" + Thread.currentThread().getName());

        return result;
    }
}