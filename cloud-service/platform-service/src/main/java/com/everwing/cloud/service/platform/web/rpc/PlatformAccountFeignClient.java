package com.everwing.cloud.service.platform.web.rpc;

import com.everwing.cloud.common.entity.platform.Account;
import com.everwing.cloud.service.platform.api.AccountApi;
import com.everwing.cloud.service.platform.biz.AccountBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DELL shiny
 * @create 2019/10/14
 */
@RefreshScope
@RestController
public class PlatformAccountFeignClient implements AccountApi {

    @Autowired
    private AccountBiz accountBiz;

    @Override
    public Account queryAccount(Account account) {
        return accountBiz.queryAccount(account);
    }
}
