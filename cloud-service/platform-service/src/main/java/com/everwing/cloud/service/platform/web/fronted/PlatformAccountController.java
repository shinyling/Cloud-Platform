package com.everwing.cloud.service.platform.web.fronted;

import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.common.entity.platform.Account;
import com.everwing.cloud.service.platform.biz.AccountBiz;
import com.everwing.cloud.service.platform.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DELL shiny
 * @create 2019/10/14
 */
@RestController
@RequestMapping("account")
public class PlatformAccountController {

    @Autowired
    private AccountBiz accountBiz;

    @PostMapping("query")
    public ResultJson<Account> queryAccount(@RequestBody Account account){
        account=accountBiz.queryAccount(account);
        return ResultJson.success(account);
    }
}
