package com.graves.meetingfilm.apigwzuul.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author Graves
 * @title: MyFilter
 * @projectName backend-parent
 * @description: TODO
 * @date 2020/3/19 23:18
 */
@Slf4j
public class MyFilter extends ZuulFilter {

    /**
    * @Author Graves
    * @Description //Filter类型
    * @Date  23:21 
    * @Param []
    * @return java.lang.String
    */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
    * @Author Graves
    * @Description //filter的执行顺序
    * @Date  23:21 
    * @Param []
    * @return int
    */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
    * @Author Graves
    * @Description //是否要拦截
    * @Date  23:21 
    * @Param []
    * @return boolean
    */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
    * @Author Graves
    * @Description //Filter的具体业务逻辑
    * @Date  23:22 
    * @Param []
    * @return java.lang.Object
    */
    @Override
    public Object run() throws ZuulException {
        // 相当于ThreadLocal
        RequestContext requestContext = RequestContext.getCurrentContext();

        HttpServletRequest request = requestContext.getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String headName = headerNames.nextElement();
            log.info("headName:{}, headValue:{}", headName, request.getHeader(headName));
        }

        return null;
    }
}
