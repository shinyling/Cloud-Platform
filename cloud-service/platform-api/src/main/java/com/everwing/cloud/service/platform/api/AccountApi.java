package com.everwing.cloud.service.platform.api;

import com.everwing.cloud.common.entity.Account;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author DELL shiny
 * @create 2019/5/9
 */
@RequestMapping("platform-api-account")
public interface AccountApi {

    @PostMapping("queryAccount")
    Account queryAccount(@RequestBody Account account);
}
