package com.everwing.cloud.service.platform.aop;

import com.alibaba.fastjson.JSON;
import com.everwing.cloud.common.vo.UserVo;
import com.everwing.cloud.service.platform.anno.SysLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @author DELL shiny
 * @date 2020/3/13
 */
@Slf4j
@Aspect
@Component
public class SystemLogAspect {

    @Pointcut("@annotation(com.everwing.cloud.service.platform.anno.SysLog)")
    public void controllerAspect() {
    }

    @Around("controllerAspect()")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return handle(joinPoint, null, request);
    }

    /**
     * 异常处理
     *
     * @param joinPoint
     * @param e
     * @throws Throwable
     */
    @AfterThrowing(pointcut = "controllerAspect()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        handle(joinPoint, e, request);
    }

    public Object handle(final JoinPoint joinPoint, final Exception e, final HttpServletRequest request) throws Throwable {
        Object proceed = null;
        UserVo userVo = null;
        String userString = JSON.toJSONString(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        if (userString.startsWith("{")) {
            userVo = JSON.parseObject(userString, UserVo.class);
        }
        //获取请求的ip
        String ip = getIpAddress(request);
        String url = request.getRequestURI();
        //获取方法执行前时间
        LocalDateTime startTime = LocalDateTime.now();
        if (e == null) {
            if (joinPoint instanceof ProceedingJoinPoint) {
                ProceedingJoinPoint proceedingJoinPoint = (ProceedingJoinPoint) joinPoint;
                proceed = proceedingJoinPoint.proceed();
            }
        }
        LocalDateTime endTime = LocalDateTime.now();
        long duration = Duration.between(startTime, endTime).toMillis();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String methodName = method.getName();
        SysLog sysLog = method.getAnnotation(SysLog.class);
        String operation = sysLog.value();
        Object[] params = joinPoint.getArgs();
        String paramStr = "";
        for (Object param : params) {
            paramStr += param;
        }
        if (e == null) {
            if (userVo != null) {
                log.info("Ip:[{}],URI:[{}],用户:[{}],操作:[{}],方法名:[{}],参数:[{}],执行耗时:[{}]毫秒,返回值:[{}]", ip, url, userVo.getId(), operation, methodName, paramStr, duration, JSON.toJSONString(proceed));
            } else {
                log.info("Ip:[{}],URI:[{}],操作:[{}],方法名:[{}],参数:[{}],执行耗时:[{}]毫秒,返回值:[{}]", ip, url, operation, methodName, paramStr, duration, JSON.toJSONString(proceed));
            }
        } else {
            if (userVo != null) {
                log.error("Ip:[{}],URI:[{}],用户:[{}],操作:[{}],方法名:[{}],参数:[{}],错误:[{}]", ip, url, userVo.getId(), operation, methodName, paramStr, e.getMessage());
            } else {
                log.info("Ip:[{}],URI:[{}],操作:[{}],方法名:[{}],参数:[{}],错误:[{}]", ip, url, operation, methodName, paramStr, e.getMessage());
            }
        }
        return proceed;
    }

    public String getIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) { //"***.***.***.***".length() = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

}
