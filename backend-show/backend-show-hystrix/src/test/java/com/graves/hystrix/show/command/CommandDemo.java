package com.graves.hystrix.show.command;

import com.netflix.hystrix.*;
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
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("CommandHelloWorld"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.defaultSetter()
                                .withRequestCacheEnabled(false)//请求缓存的开关
                                // 切换线程池隔离还是信号量隔离
                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)
                                .withExecutionIsolationSemaphoreMaxConcurrentRequests(2)
//                                .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)
                        .withCircuitBreakerRequestVolumeThreshold(2)// 单位时间得请求阈值
                        .withCircuitBreakerErrorThresholdPercentage(50)// 当满足请i去阈值时，超过50%则开启熔断
                ).andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("MyThread"))
//                    .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.defaultSetter()
//                            .withCoreSize(2)
//                            .withMaximumSize(3).withAllowMaximumSizeToDivergeFromCoreSize(true)
//                            .withMaxQueueSize(2))
        );
        this.name = name;
    }

    @Override
    protected String getFallback() {
        String result = "Fallback name : "+ name;

        System.err.println(result+" , currentThread-"+Thread.currentThread().getName());

        return result;
    }

    @Override
    protected String run() throws Exception {
        String result = "CommandHelloWorld name:" + name;

        if(name.startsWith("graves")){
            int i = 1/0;
        }

        //Thread.sleep(800L);
        System.err.println(result + "currentThread-" + Thread.currentThread().getName());

        return result;
    }

    @Override
    protected String getCacheKey() {
        return String.valueOf(name);
    }
}
