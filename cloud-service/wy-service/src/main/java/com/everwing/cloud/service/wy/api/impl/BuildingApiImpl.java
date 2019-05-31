package com.everwing.cloud.service.wy.api.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.everwing.cloud.service.wy.api.BuildingApi;
import com.everwing.cloud.service.wy.context.WyBusinessContext;
import com.everwing.cloud.service.wy.entity.Building;
import com.everwing.cloud.service.wy.service.IBuildingService;
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
        String companyId= WyBusinessContext.getCompanyId();
        return buildingService.getOne(companyId,queryWrapper);
    }
}
