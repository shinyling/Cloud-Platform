package com.everwing.cloud.web.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author DELL shiny
 * @create 2019/5/9
 */
@EnableOAuth2Sso
@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
public class PlatformWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlatformWebApplication.class,args);
    }
}
