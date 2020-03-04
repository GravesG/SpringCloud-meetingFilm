package com.graves.hystrix.show.command;

import com.google.common.collect.Lists;
import com.netflix.hystrix.*;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author Graves
 * @title: CommandCollapser
 * @projectName backend-parent
 * @description: 请求合并处理对象
 * @date 2020/3/4 21:19
 */
public class CommandCollapser extends HystrixCollapser<List<String>,String,Integer> {

    private Integer id;

    public CommandCollapser(Integer id){
        super(Setter.withCollapserKey(HystrixCollapserKey.Factory.asKey("CommandCollapser"))
                );
        this.id = id;
    }

    /**
    * @Author Graves
    * @Description 获取请求参数
    * @Date  21:20 
    * @Param []
    * @return java.lang.Integer
    */
    @Override
    public Integer getRequestArgument() {
        return id;
    }

    /**
    * @Author Graves
    * @Description 批量业务处理
    * @Date  21:20 
    * @Param [collection]
    * @return com.netflix.hystrix.HystrixCommand<java.util.List<java.lang.String>>
    */
    @Override
    protected HystrixCommand<List<String>> createCommand(Collection<CollapsedRequest<String, Integer>> collection) {
        return new BatchCommand(collection);
    }

    /**
    * @Author Graves
    * @Description 批量处理结果与请求业务之间映射关系处理
    * @Date  21:20 
    * @Param [strings, collection]
    * @return void
    */
    @Override
    protected void mapResponseToRequests(List<String> strings, Collection<CollapsedRequest<String, Integer>> collection) {
        int counts = 0;
        Iterator<HystrixCollapser.CollapsedRequest<String, Integer>> iterator = collection.iterator();
        while (iterator.hasNext()) {
            HystrixCollapser.CollapsedRequest<String, Integer> response = iterator.next();

            String result = strings.get(counts++);

            response.setResponse(result);
        }
    }
}
class BatchCommand extends HystrixCommand <List<String>> {

    private Collection<HystrixCollapser.CollapsedRequest<String, Integer>> collection;

    public BatchCommand(Collection<HystrixCollapser.CollapsedRequest<String, Integer>> collection){
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("BatchCommand")));
        this.collection = collection;
    }

    @Override
    protected List<String> run() throws Exception {
        System.err.println("currentThread :"+Thread.currentThread().getName());
        List<String> result = Lists.newArrayList();

        Iterator<HystrixCollapser.CollapsedRequest<String, Integer>> iterator = collection.iterator();
        while(iterator.hasNext()){
            HystrixCollapser.CollapsedRequest<String, Integer> request = iterator.next();

            Integer reqParam = request.getArgument();

            //具体业务逻辑
            result.add("Graves req:"+reqParam);
        }

        return result;
    }
}