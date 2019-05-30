package com.everwing.cloud.service.wy.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.everwing.cloud.service.wy.entity.Building;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shiny
 * @since 2019-05-10
 */
public interface IBuildingService extends IService<Building> {

    Building getOne(String companyId, QueryWrapper<Building> queryWrapper);
}
