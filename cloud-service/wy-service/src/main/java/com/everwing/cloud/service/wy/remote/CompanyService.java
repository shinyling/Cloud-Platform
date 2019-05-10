package com.everwing.cloud.service.wy.remote;

import com.everwing.cloud.service.platform.api.CompanyApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author DELL shiny
 * @create 2019/5/10
 */
@FeignClient("platform-service")
public interface CompanyService extends CompanyApi {
}
