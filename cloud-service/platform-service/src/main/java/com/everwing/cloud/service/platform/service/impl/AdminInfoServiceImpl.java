package com.everwing.cloud.service.platform.service.impl;

import com.everwing.cloud.service.platform.entity.AdminInfo;
import com.everwing.cloud.service.platform.mapper.AdminInfoMapper;
import com.everwing.cloud.service.platform.service.IAdminInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * admin额外信息表 服务实现类
 * </p>
 *
 * @author shiny
 * @since 2020-04-07
 */
@Service
public class AdminInfoServiceImpl extends ServiceImpl<AdminInfoMapper, AdminInfo> implements IAdminInfoService {

}
