package com.graves.meetingfilm.consumer.ribbon;

import com.netflix.client.ClientException;
import com.netflix.client.ClientFactory;
import com.netflix.client.http.HttpRequest;
import com.netflix.client.http.HttpResponse;
import com.netflix.config.ConfigurationManager;
import com.netflix.loadbalancer.ZoneAwareLoadBalancer;
import com.netflix.niws.client.http.HttpClientRequest;
import com.netflix.niws.client.http.RestClient;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author : graves
 * @program : com.graves.meetingfilm.consumer.ribbon
 * @description :ribbon 的使用
 **/
public class App {

    public static void main(String[] args) throws IOException, ClientException, URISyntaxException, InterruptedException {
        // 读取配置文件
        ConfigurationManager.loadPropertiesFromResources("graves.properties");  // 1
        System.err.println(ConfigurationManager.getConfigInstance().getProperty("graves-client.ribbon.listOfServers"));

        // 构建一个HttpClient，serverList固定的情况
        RestClient client = (RestClient) ClientFactory.getNamedClient("graves-client");  // 2
        HttpRequest request = HttpRequest.newBuilder().uri(new URI("/")).build(); // 3

        for (int i = 0; i < 5; i++)  {
            HttpResponse response = client.executeWithLoadBalancer(request); // 4
            System.err.println("Status code for " + response.getRequestedURI() + "  :" + response.getStatus());
        }

        // 动态修改的serverList
        ZoneAwareLoadBalancer lb = (ZoneAwareLoadBalancer) client.getLoadBalancer();
        System.err.println(lb.getLoadBalancerStats());
        ConfigurationManager.getConfigInstance().setProperty(
                "graves-client.ribbon.listOfServers", "www.qq.com:80,www.taobao.com:80"); // 5
        System.err.println("changing servers ...");
        Thread.sleep(3000); // 6
        for (int i = 0; i < 5; i++)  {
            HttpResponse response = client.executeWithLoadBalancer(request);
            System.err.println("Status code for " + response.getRequestedURI() + "  : " + response.getStatus());
        }
        System.out.println(lb.getLoadBalancerStats()); // 7

        // 最好的情况就是Ribbon和注册中心结合使用

    }

}
