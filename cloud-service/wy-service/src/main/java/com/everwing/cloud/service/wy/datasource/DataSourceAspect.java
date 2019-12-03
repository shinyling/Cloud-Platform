package com.everwing.cloud.service.wy.datasource;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.everwing.cloud.common.vo.UserVo;

import lombok.extern.slf4j.Slf4j;

/**
 * @author DELL shiny
 * @create 2019/5/10
 */
@Slf4j
@Component
@Aspect
@Order(-1)
public class DataSourceAspect {

    @Pointcut("execution(* com.everwing.cloud.service.wy.service.*.*(..))")
    public void service(){}

    @Before("service()")
    public void dataSourceChange(JoinPoint joinPoint){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        log.info("Enter DataSourceAOP，authentication:[{}]",JSON.toJSONString(authentication.getPrincipal()));
        UserVo user=JSON.parseObject(JSON.toJSONString(authentication.getPrincipal()),UserVo.class);
        String companyId = StringUtils.defaultIfBlank(user.getCompanyId(), DataSourceUtil.DEFAULT);
        DataBaseContextHolder.setCompanyId(companyId);
    }

    @After("service()")
    public void doAfter() {
        DataBaseContextHolder.clearCompanyId();
    }

}
