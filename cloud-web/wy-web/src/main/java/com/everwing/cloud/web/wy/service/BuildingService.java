package com.everwing.cloud.web.wy.service;

import com.everwing.cloud.service.wy.api.BuildingApi;
import com.everwing.cloud.web.wy.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author DELL shiny
 * @create 2019/5/13
 */
@FeignClient(value = "wy-service",configuration = FeignConfig.class)
public interface BuildingService extends BuildingApi {
}
