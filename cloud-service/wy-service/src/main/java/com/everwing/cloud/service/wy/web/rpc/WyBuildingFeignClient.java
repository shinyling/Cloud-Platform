package com.everwing.cloud.service.wy.web.rpc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.everwing.cloud.service.wy.api.BuildingApi;
import com.everwing.cloud.service.wy.biz.BuildingBiz;
import com.everwing.cloud.service.wy.entity.Building;
import com.everwing.cloud.service.wy.service.IBuildingService;
import com.everwing.cloud.service.wy.vo.BuildingVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DELL shiny
 * @create 2019/5/10
 */
@RestController
public class WyBuildingFeignClient implements BuildingApi {

    @Autowired
    private BuildingBiz buildingBiz;

    @Override
    public BuildingVo queryBuilding(@RequestBody BuildingVo buildingVo) {
        return buildingBiz.queryBuilding(buildingVo);
    }
}
