package com.everwing.cloud.service.wy.api.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.everwing.cloud.platform.service.IBuildingService;
import com.everwing.cloud.service.wy.api.BuildingApi;
import com.everwing.cloud.service.wy.entity.Building;
import com.mysql.cj.x.protobuf.MysqlxExpect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DELL shiny
 * @create 2019/5/10
 */
@RestController
public class BuildingApiImpl implements BuildingApi {

    @Autowired
    private IBuildingService buildingService;

    @Override
    public Building queryBuilding(@RequestBody Building building) {
        QueryWrapper<Building> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Building::getHouseCode, building.getHouseCode());
        return buildingService.getOne(queryWrapper);
    }
}
