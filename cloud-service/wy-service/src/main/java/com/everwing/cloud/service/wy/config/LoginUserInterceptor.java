package com.everwing.cloud.service.wy.config;

import com.everwing.cloud.common.entity.Account;
import com.everwing.cloud.service.wy.context.WyBusinessContext;
import com.everwing.cloud.service.wy.remote.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author DELL shiny
 * @create 2019/5/31
 */
@Slf4j
@Configuration
public class LoginUserInterceptor implements HandlerInterceptor {

    @Autowired
    private AccountService accountService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String username = request.getHeader("username");
        if(StringUtils.isEmpty(username)) {
            log.error("未找到登录信息");
            return false;
        }
        Account account=new Account();
        account.setName(username);
        account=accountService.queryAccount(account);
        log.info("查询到登录信息:{}",account);
        WyBusinessContext.setAccount(account);
        return true;
    }
}
