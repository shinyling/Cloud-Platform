package com.everwing.cloud.web.platform.controller;

import com.everwing.cloud.common.entity.Account;
import com.everwing.cloud.web.platform.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Account query(@RequestBody Account account){
        return accountService.queryAccount(account);
    }
}
