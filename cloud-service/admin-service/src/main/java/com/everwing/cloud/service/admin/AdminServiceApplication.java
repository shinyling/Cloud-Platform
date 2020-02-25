package com.everwing.cloud.service.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author DELL shiny
 * @create 2020/2/25
 */
@EnableDiscoveryClient
@SpringBootApplication
public class AdminServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(AdminServiceApplication.class,args);

    }
}
