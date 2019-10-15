package com.everwing.cloud.service.platform.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.everwing.cloud.common.entity.platform.Account;
import com.everwing.cloud.service.platform.entity.User;
import com.everwing.cloud.service.platform.service.IAccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author DELL shiny
 * @create 2019/10/14
 */
@Component
public class AccountBiz {

    @Autowired
    private IAccountService accountService;

    public Account queryAccount(Account account){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",account.getUsername());
        queryWrapper.eq("type",1);
        User user=accountService.getOne(queryWrapper);
        BeanUtils.copyProperties(user,account);
        return account;
    }
}
