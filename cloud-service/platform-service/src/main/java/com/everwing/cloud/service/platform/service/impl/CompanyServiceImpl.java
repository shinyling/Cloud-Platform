package com.everwing.cloud.service.platform.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.everwing.cloud.service.platform.entity.Company;
import com.everwing.cloud.service.platform.mapper.CompanyMapper;
import com.everwing.cloud.service.platform.service.ICompanyService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shiny
 * @since 2019-05-07
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements ICompanyService {

}
