package com.everwing.cloud.service.platform.service.impl;

import com.everwing.cloud.service.platform.entity.Account;
import com.everwing.cloud.service.platform.mapper.AccountMapper;
import com.everwing.cloud.service.platform.service.IAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shiny
 * @since 2020-04-07
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {

}
