package com.everwing.cloud.service.platform.api;

import com.everwing.cloud.common.entity.platform.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author DELL shiny
 * @create 2019/5/9
 */
@FeignClient(value = "cloud-provider-platform")
public interface AccountApi {

    @PostMapping("queryAccount")
    Account queryAccount(@RequestBody Account account);
}
