package com.everwing.cloud.service.platform.biz;

import com.everwing.cloud.service.platform.service.ICompanyService;
import com.everwing.cloud.service.platform.vo.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author DELL shiny
 * @create 2019/10/16
 */
@Component
public class CompanyBiz {

    @Autowired
    private ICompanyService companyService;

    public List<Company> listAll(){
        return companyService.list();
    }

    public Company selectById(String companyId) {
        return companyService.getById(companyId);
    }

    public Company add(Company company) {
        boolean flag=companyService.save(company);
        if(flag){
            return company;
        }
        return null;
    }
}
