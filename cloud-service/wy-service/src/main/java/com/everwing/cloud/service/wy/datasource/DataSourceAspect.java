package com.everwing.cloud.service.wy.datasource;

import com.everwing.cloud.service.wy.context.WyBusinessContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author DELL shiny
 * @create 2019/5/10
 */
@Component
@Order(-10)
@Aspect
public class DataSourceAspect {

    @Pointcut("execution(* com.everwing.cloud.service.wy.api.impl.*.*(..))")
    public void serviceImpl(){}

    @Before("serviceImpl()")
    public void dataSourceChange(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        Object obj=args[0];
        if(obj instanceof WyBusinessContext) {
            WyBusinessContext context = (WyBusinessContext) args[0];
            DataBaseContextHolder.setCompanyId(context.getCompanyId());
        }else {
            System.out.println("参数错误，第一个参数必须context");
        }
    }
}
