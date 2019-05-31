package com.everwing.cloud.web.platform.controller;

import com.everwing.cloud.common.entity.Account;
import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.web.platform.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DELL shiny
 * @create 2019/5/9
 */
@RestController
@RequestMapping("account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("query")
    public ResultJson query(@RequestBody Account account){
        return ResultJson.success(accountService.queryAccount(account));
    }
}
