package com.everwing.cloud.service.wy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author DELL shiny
 * @create 2019/5/10
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.everwing.cloud.service.wy.mapper")
@EnableFeignClients
public class WyServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WyServiceApplication.class,args);
    }
}
