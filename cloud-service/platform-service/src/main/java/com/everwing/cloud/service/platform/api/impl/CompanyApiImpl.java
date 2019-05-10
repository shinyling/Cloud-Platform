package com.everwing.cloud.service.platform.api.impl;

import com.everwing.cloud.service.platform.api.CompanyApi;
import com.everwing.cloud.service.platform.entity.Company;
import com.everwing.cloud.service.platform.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author DELL shiny
 * @create 2019/5/9
 */
@RestController
public class CompanyApiImpl implements CompanyApi {

    @Autowired
    private ICompanyService companyService;

    @Override
    public List<Company> list(){
        return companyService.list();
    }
}
