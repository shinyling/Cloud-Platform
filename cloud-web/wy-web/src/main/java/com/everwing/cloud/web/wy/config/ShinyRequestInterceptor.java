package com.everwing.cloud.web.wy.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author DELL shiny
 * @create 2019/5/31
 */
@Configuration
public class ShinyRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String username= (String) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        if(StringUtils.isNotBlank(username)){
            requestTemplate.header("username",username);
        }
    }
}
