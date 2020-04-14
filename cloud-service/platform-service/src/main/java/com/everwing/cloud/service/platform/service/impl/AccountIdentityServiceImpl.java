package com.everwing.cloud.service.platform.service.impl;

import com.everwing.cloud.service.platform.entity.AccountIdentity;
import com.everwing.cloud.service.platform.mapper.AccountIdentityMapper;
import com.everwing.cloud.service.platform.service.IAccountIdentityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 身份认证表 服务实现类
 * </p>
 *
 * @author shiny
 * @since 2020-04-07
 */
@Service
public class AccountIdentityServiceImpl extends ServiceImpl<AccountIdentityMapper, AccountIdentity> implements IAccountIdentityService {

}
