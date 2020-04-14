package com.everwing.cloud.service.wy.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.everwing.cloud.service.wy.entity.Building;
import com.everwing.cloud.service.wy.service.IBuildingService;
import com.everwing.cloud.service.wy.vo.BuildingVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author DELL shiny
 * @create 2019/10/16
 */
@Component
public class BuildingBiz {

    @Autowired
    private IBuildingService buildingService;

    public BuildingVo queryBuilding(BuildingVo buildingVo) {
        QueryWrapper<Building> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Building::getHouseCode, buildingVo.getHouseCode());
        Building building = buildingService.getOne(queryWrapper);
        if (building != null) {
            BeanUtils.copyProperties(building, buildingVo);
        }
        return buildingVo;
    }
}
