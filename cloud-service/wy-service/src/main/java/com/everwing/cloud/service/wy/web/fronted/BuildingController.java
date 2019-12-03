package com.everwing.cloud.service.wy.web.fronted;

import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.service.wy.biz.BuildingBiz;
import com.everwing.cloud.service.wy.vo.BuildingVo;
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
public class BuildingController {

    @Autowired
    private BuildingBiz buildingBiz;

    @PostMapping("queryBuilding")
    public ResultJson queryBuilding(@RequestBody BuildingVo building){
        return ResultJson.success(buildingBiz.queryBuilding(building));
    }
}