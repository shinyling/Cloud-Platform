package com.everwing.cloud.service.platform.api.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.everwing.cloud.common.entity.Account;
import com.everwing.cloud.service.platform.api.AccountApi;
import com.everwing.cloud.service.platform.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DELL shiny
 * @create 2019/5/9
 */
@RestController
public class AccountApiImpl implements AccountApi {

    @Autowired
    private IAccountService accountService;

    @Override
    public Account queryAccount(Account account) {
        QueryWrapper<Account> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("name",account.getName());
        queryWrapper.eq("type",1);
        return accountService.getOne(queryWrapper);
    }
}
