package com.everwing.cloud.web.platform.service;

import com.everwing.cloud.service.platform.api.AccountApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author DELL shiny
 * @create 2019/5/9
 */
@FeignClient("platform-service")
public interface AccountService extends AccountApi {
}
