package com.everwing.cloud.service.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.everwing.cloud.service.platform.entity.AccountAndHouse;

import java.util.List;

/**
 * <p>
 * 账号和房屋绑定关系表 服务类
 * </p>
 *
 * @author shiny
 * @since 2020-04-07
 */
public interface IAccountAndHouseService extends IService<AccountAndHouse> {

    boolean removeByUnionIds(List<AccountAndHouse> accountAndHouseList);
}
