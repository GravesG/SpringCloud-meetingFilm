package com.graves.meetingfilm.apigwzuul.fallbacks;

import com.alibaba.fastjson.JSONObject;
import com.graves.meetingfilm.utils.common.vo.BaseResponseVO;
import com.graves.meetingfilm.utils.exception.CommonServiceException;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Graves
 * @title: MyFallback
 * @projectName backend-parent
 * @description: TODO
 * @date 2020/3/21 21:01
 */
@Component
public class MyFallback implements FallbackProvider {

    /**
    * @Author Graves
    * @Description //针对哪一个路由进行降级， return可以写 *
    * @Date  21:02
    * @Param []
    * @return java.lang.String
    */
    @Override
    public String getRoute() {
        return "film-service";
    }

    /**
    * @Author Graves
    * @Description //降级时处理方式
    * @Date  21:02
    * @Param [route, cause]
    * @return org.springframework.http.client.ClientHttpResponse
    */
    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return 200;
            }

            @Override
            public String getStatusText() throws IOException {
                return "OK";
            }

            @Override
            public void close() {

            }

            /**
            * @Author Graves
            * @Description 业务降级处理方式
            * @Date  21:02
            * @Param []
            * @return java.io.InputStream
            */
            @Override
            public InputStream getBody() throws IOException {
                BaseResponseVO responseVO
                        = BaseResponseVO.serviceException(
                        new CommonServiceException(404, "No Films!~"));
                String result = JSONObject.toJSONString(responseVO);
                return new ByteArrayInputStream(result.getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }
}
