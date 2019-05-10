package com.everwing.cloud.service.wy.api;

import com.everwing.cloud.service.wy.entity.Building;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author DELL shiny
 * @create 2019/5/10
 */
@RequestMapping("wy-service-building")
public interface BuildingApi {

    @PostMapping("queryBuilding")
    Building queryBuilding(@RequestBody Building building);
}
