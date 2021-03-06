package com.everwing.cloud.service.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.everwing.cloud.service.platform.entity.User;
import com.everwing.cloud.service.platform.mapper.AccountMapper;
import com.everwing.cloud.service.platform.service.IAccountService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shiny
 * @since 2019-05-09
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, User> implements IAccountService {

}
