package com.everwing.cloud.service.wy.api.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.everwing.cloud.common.entity.Account;
import com.everwing.cloud.service.wy.api.BuildingApi;
import com.everwing.cloud.service.wy.entity.Building;
import com.everwing.cloud.service.wy.remote.AccountService;
import com.everwing.cloud.service.wy.service.IBuildingService;
import org.apache.commons.lang3.StringUtils;
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

    @Autowired
    private AccountService accountService;

    @Override
    public Building queryBuilding(@RequestBody Building building) {
        QueryWrapper<Building> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Building::getHouseCode, building.getHouseCode());
        String username=building.getUsername();
        String companyId=null;
        if(StringUtils.isNotBlank(username)){
            Account account=new Account();
            account.setName(username);
            account=accountService.queryAccount(account);
            companyId=account.getCompanyId();
        }
        if(StringUtils.isEmpty(companyId)){
            throw new RuntimeException("无法切换数据源，系统中断！");
        }
        return buildingService.getOne(companyId,queryWrapper);
    }
}
