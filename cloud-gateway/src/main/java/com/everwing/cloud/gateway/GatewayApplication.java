package com.everwing.cloud.gateway;

import com.everwing.cloud.gateway.filter.ErrorFilter;
import com.everwing.cloud.gateway.filter.TokenAccessFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;
import org.springframework.context.annotation.Bean;

/**
 * @author DELL shiny
 * @create 2019/5/14
 */
@EnableZuulProxy
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class,args);
    }

    @Bean
    public TokenAccessFilter accessFilter() {
        return new TokenAccessFilter();
    }

    @Bean
    public ErrorFilter errorFilter() {
        return new ErrorFilter();
    }

}
