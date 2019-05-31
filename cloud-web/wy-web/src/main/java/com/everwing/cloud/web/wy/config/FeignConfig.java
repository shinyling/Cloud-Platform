package com.everwing.cloud.web.wy.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author DELL shiny
 * @create 2019/5/13
 */
@Configuration
public class FeignConfig implements RequestInterceptor {

    final String TOKEN="token";

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        requestTemplate.header(TOKEN, request.getHeader(TOKEN));
    }
}
