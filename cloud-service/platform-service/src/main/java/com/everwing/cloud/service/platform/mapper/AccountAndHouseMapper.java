package com.everwing.cloud.service.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.everwing.cloud.service.platform.entity.AccountAndHouse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 账号和房屋绑定关系表 Mapper 接口
 * </p>
 *
 * @author shiny
 * @since 2020-04-07
 */
public interface AccountAndHouseMapper extends BaseMapper<AccountAndHouse> {

    int removeByUnionIds(@Param("accountAndHouseList") List<AccountAndHouse> accountAndHouseList);
}
