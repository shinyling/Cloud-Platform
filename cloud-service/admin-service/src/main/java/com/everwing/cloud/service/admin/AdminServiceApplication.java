package com.everwing.cloud.service.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author DELL shiny
 * @create 2020/2/25
 */
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.everwing.cloud.service.admin.mapper")
public class AdminServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(AdminServiceApplication.class,args);

    }
}
