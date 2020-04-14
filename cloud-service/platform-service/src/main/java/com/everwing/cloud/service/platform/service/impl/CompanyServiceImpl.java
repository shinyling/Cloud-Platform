package com.everwing.cloud.service.platform.service.impl;

import com.everwing.cloud.service.platform.entity.Company;
import com.everwing.cloud.service.platform.mapper.CompanyMapper;
import com.everwing.cloud.service.platform.service.ICompanyService;
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
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements ICompanyService {

}
