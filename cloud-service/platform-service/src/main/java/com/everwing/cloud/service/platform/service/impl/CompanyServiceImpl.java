package com.everwing.cloud.service.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.everwing.cloud.service.platform.entity.Company;
import com.everwing.cloud.service.platform.mapper.CompanyMapper;
import com.everwing.cloud.service.platform.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

    @Value("spring.datasource.username")
    private String username;

    @Value("spring.datasource.password")
    private String password;

    @Value("spring.datasource.url")
    private String url;

    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public Company createSchema(Company company) {
        company.setJdbcUsername(username);
        company.setJdbcPassword(password);
        boolean flag=save(company);
        if(flag){
            String dbName=company.getCompanyId();
            String jdbcUrl=url.replace("ucenter",dbName);
            companyMapper.createSchema(dbName,username,password);
            company.setJdbcUrl(jdbcUrl);
            company.setUpdateTime(LocalDateTime.now());
            company.setUpdateBy();
        }
        return null;
    }
}
