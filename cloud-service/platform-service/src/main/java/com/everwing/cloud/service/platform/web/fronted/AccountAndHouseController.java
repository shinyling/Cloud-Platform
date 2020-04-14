package com.everwing.cloud.service.platform.web.fronted;


import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.service.platform.anno.SysLog;
import com.everwing.cloud.service.platform.biz.AccountAndHouseBiz;
import com.everwing.cloud.service.platform.entity.AccountAndHouse;
import com.everwing.cloud.service.platform.group.AddGroup;
import com.everwing.cloud.service.platform.validate.ValidList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 账号和房屋绑定关系表
 * </p>
 *
 * @author shiny
 * @since 2020-04-07
 */
@Api(tags = "房屋账号绑定关系")
@RestController
@RequestMapping("/accountAndHouse")
public class AccountAndHouseController {

    @Autowired
    private AccountAndHouseBiz accountAndHouseBiz;

    @ApiOperation("房屋账号绑定")
    @PostMapping("bind")
    @SysLog("房屋账号绑定")
    public ResultJson bind(@Validated(AddGroup.class) @RequestBody ValidList<AccountAndHouse> accountAndHouseList) {
        return accountAndHouseBiz.bind(accountAndHouseList);
    }

    @ApiOperation("房屋账号解绑")
    @PostMapping("unBind")
    @SysLog("房屋账号解绑")
    public ResultJson unBind(@Validated(AddGroup.class) @RequestBody ValidList<AccountAndHouse> accountAndHouseList) {
        return accountAndHouseBiz.unBind(accountAndHouseList);
    }
}
