package com.everwing.cloud.web.wy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author DELL shiny
 * @create 2019/5/13
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class WyWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WyWebApplication.class,args);
    }
}
