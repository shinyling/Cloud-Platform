package com.everwing.cloud.service.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.everwing.cloud.service.platform.entity.AccountAndHouse;
import com.everwing.cloud.service.platform.mapper.AccountAndHouseMapper;
import com.everwing.cloud.service.platform.service.IAccountAndHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 账号和房屋绑定关系表 服务实现类
 * </p>
 *
 * @author shiny
 * @since 2020-04-07
 */
@Service
public class AccountAndHouseServiceImpl extends ServiceImpl<AccountAndHouseMapper, AccountAndHouse> implements IAccountAndHouseService {

    @Autowired
    private AccountAndHouseMapper accountAndHouseMapper;

    @Override
    public boolean removeByUnionIds(List<AccountAndHouse> accountAndHouseList) {
        return accountAndHouseMapper.removeByUnionIds(accountAndHouseList) == accountAndHouseList.size();
    }
}
