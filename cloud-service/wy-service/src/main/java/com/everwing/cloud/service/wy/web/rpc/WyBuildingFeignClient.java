package com.everwing.cloud.service.wy.web.rpc;

import com.alibaba.dubbo.config.annotation.Service;
import com.everwing.cloud.service.wy.api.BuildingApi;
import com.everwing.cloud.service.wy.biz.BuildingBiz;
import com.everwing.cloud.service.wy.vo.BuildingVo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author DELL shiny
 * @create 2019/5/10
 */
@Service
public class WyBuildingFeignClient implements BuildingApi {

    @Autowired
    private BuildingBiz buildingBiz;

    @Override
    public BuildingVo queryBuilding(BuildingVo buildingVo) {
        return buildingBiz.queryBuilding(buildingVo);
    }
}
