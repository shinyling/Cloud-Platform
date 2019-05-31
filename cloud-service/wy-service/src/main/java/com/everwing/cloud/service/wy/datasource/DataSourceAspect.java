package com.everwing.cloud.service.wy.datasource;

import com.everwing.cloud.service.wy.remote.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author DELL shiny
 * @create 2019/5/10
 */
@Slf4j
@Component
@Aspect
@Order(-1)
public class DataSourceAspect {


    @Autowired
    private CompanyService companyService;

    @Pointcut("execution(* com.everwing.cloud.service.wy.service.*.*(..))")
    public void service(){}

    @Before("service()")
    public void dataSourceChange(JoinPoint joinPoint){
        log.info("Enter DataSourceAOP");
        String companyId = null;
        Object[] args = joinPoint.getArgs();
        if (args.length >= 1) {
            companyId = String.valueOf(args[0]);
        }
        companyId = StringUtils.defaultIfBlank(companyId, DataSourceUtil.DEFAULT);
        DataBaseContextHolder.setCompanyId(companyId);
    }

    @After("service()")
    public void doAfter() {
        DataBaseContextHolder.clearCompanyId();
    }

}
