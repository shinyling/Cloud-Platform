package com.everwing.cloud.service.wy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author DELL shiny
 * @create 2019/5/31
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Bean
    public LoginUserInterceptor loginUserInterceptor() {
        return new LoginUserInterceptor();
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginUserInterceptor());
    }
}
