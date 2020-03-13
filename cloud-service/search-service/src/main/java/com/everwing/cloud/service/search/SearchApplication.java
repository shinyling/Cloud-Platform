package com.everwing.cloud.service.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author DELL shiny
 * @create 2020/3/9
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class,args);
    }

}
