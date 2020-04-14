package com.everwing.cloud.service.platform.biz;

import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.service.platform.entity.AccountAndHouse;
import com.everwing.cloud.service.platform.service.IAccountAndHouseService;
import com.everwing.cloud.service.platform.validate.ValidList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author DELL shiny
 * @date 2020/4/8
 */
@Component
@Slf4j
public class AccountAndHouseBiz {

    @Autowired
    private IAccountAndHouseService accountAndHouseService;

    public ResultJson bind(ValidList<AccountAndHouse> accountAndHouseList) {
        boolean flag = accountAndHouseService.saveBatch(accountAndHouseList);
        if (flag) {
            return ResultJson.successWithMsg("绑定成功!");
        }
        return ResultJson.fail("绑定失败!");
    }

    public ResultJson unBind(ValidList<AccountAndHouse> accountAndHouseList) {
        boolean flag = accountAndHouseService.removeByUnionIds(accountAndHouseList);
        if (flag) {
            return ResultJson.successWithMsg("解绑成功!");
        }
        return ResultJson.fail("解绑失败!");
    }
}
