package com.everwing.cloud.service.platform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author DELL shiny
 * @create 2019/5/9
 */
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.everwing.cloud.service.platform.mapper")
public class PlatformServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlatformServiceApplication.class,args);
    }
}
