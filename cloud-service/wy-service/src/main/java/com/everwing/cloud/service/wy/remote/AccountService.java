package com.everwing.cloud.service.wy.remote;

import com.everwing.cloud.service.platform.api.AccountApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author DELL shiny
 * @create 2019/5/30
 */
@FeignClient("platform-service")
public interface AccountService extends AccountApi {
}
