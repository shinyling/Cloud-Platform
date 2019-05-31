package com.everwing.cloud.web.wy.controller;

import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.service.wy.entity.Building;
import com.everwing.cloud.web.wy.service.BuildingService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DELL shiny
 * @create 2019/5/13
 */
@RestController
@RequestMapping("building")
@Api(value = "building")
public class BuildingController {

    @Autowired
    private BuildingService buildingService;

    @PostMapping("queryBuilding")
    public ResultJson queryBuilding(@RequestBody Building building){
        return ResultJson.success(buildingService.queryBuilding(building));
    }
}
