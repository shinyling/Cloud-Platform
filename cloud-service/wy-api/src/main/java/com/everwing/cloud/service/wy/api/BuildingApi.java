package com.everwing.cloud.service.wy.api;

import com.everwing.cloud.service.wy.vo.BuildingVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author DELL shiny
 * @create 2019/5/10
 */
@FeignClient(value = "cloud-provider-wy")
public interface BuildingApi {

    @PostMapping("queryBuilding")
    BuildingVo queryBuilding(@RequestBody BuildingVo building);
}
