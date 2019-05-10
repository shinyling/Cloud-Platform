package com.everwing.cloud.web.platform.service;

import com.everwing.cloud.service.platform.api.CompanyApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author DELL shiny
 * @create 2019/5/9
 */
@FeignClient("platform-service")
public interface CompanyService extends CompanyApi {
}
