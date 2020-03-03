package com.graves.hystrix.show.command;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.checkerframework.checker.units.qual.C;
import org.junit.Test;
import rx.Observable;
import rx.Subscriber;

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

    @Test
    public void observeTest(){
        long beginTime = System.currentTimeMillis();

        CommandDemo commandDemo = new CommandDemo("observe");

        Observable<String> observe = commandDemo.observe();

        // 阻塞式调用
        String result = observe.toBlocking().single();

        long endTime = System.currentTimeMillis();
        System.out.println("result="+result+" , speeding="+(endTime-beginTime));


        // 非阻塞式调用
        observe.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.err.println("observe , onCompleted");
            }

            @Override
            public void onError(Throwable throwable) {
                System.err.println("observe , onError - throwable="+throwable);
            }

            @Override
            public void onNext(String result) {
                long endTime = System.currentTimeMillis();
                System.err.println("observe , onNext result="+result+" speend:"+(endTime - beginTime));
            }
        });
    }

    @Test
    public void toObserveTest() throws InterruptedException {
        long beginTime = System.currentTimeMillis();

        CommandDemo commandDemo1 = new CommandDemo("toObservable1");

        Observable<String> toObservable1 = commandDemo1.toObservable();

        // 阻塞式调用
        String result = toObservable1.toBlocking().single();

        long endTime = System.currentTimeMillis();
        System.out.println("result="+result+" , speeding="+(endTime-beginTime));

        CommandDemo commandDemo2 = new CommandDemo("toObservable2");
        Observable<String> toObservable2 = commandDemo2.toObservable();
        // 非阻塞式调用
        toObservable2.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.err.println("toObservable , onCompleted");
            }

            @Override
            public void onError(Throwable throwable) {
                System.err.println("toObservable , onError - throwable="+throwable);
            }

            @Override
            public void onNext(String result) {
                long endTime = System.currentTimeMillis();
                System.err.println("toObservable , onNext result="+result+" speend:"+(endTime - beginTime));
            }
        });

        Thread.sleep(2000l);
    }

    /**
    * @Author Graves
    * @Description //请求缓存
    * @Date  23:58
    * @Param []
    * @return void
    */
    @Test
    public void requestCache(){
        // 开启请求上下文
        HystrixRequestContext requestContext = HystrixRequestContext.initializeContext();
        long beginTime = System.currentTimeMillis();

        CommandDemo c1 = new CommandDemo("c1");
        CommandDemo c2 = new CommandDemo("c2");
        CommandDemo c3 = new CommandDemo("c1");

        // 第一次请求
        String r1 = c1.execute();

        System.out.println("result="+r1+" , speeding="+(System.currentTimeMillis()-beginTime));

        // 第二次请求
        String r2 = c2.execute();

        System.out.println("result="+r2+" , speeding="+(System.currentTimeMillis()-beginTime));

        // 第三次请求
        String r3 = c3.execute();
        System.out.println("result="+r3+" , speeding="+(System.currentTimeMillis()-beginTime));

        // 请求上下文关闭
        requestContext.close();
    }
}
