package com.everwing.cloud.service.platform.biz;

import com.everwing.cloud.service.platform.entity.Company;
import com.everwing.cloud.service.platform.service.ICompanyService;
import com.everwing.cloud.service.platform.vo.CompanyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author DELL shiny
 * @create 2019/10/16
 */
@Component
public class CompanyBiz {

    @Autowired
    private ICompanyService companyService;

    public List<CompanyVo> listAll(){
        return companyService.list().stream().map(company -> company.convertToVo()).collect(Collectors.toList());
    }

    public CompanyVo selectById(String companyId) {
        return companyService.getById(companyId).convertToVo();
    }

    public CompanyVo add(CompanyVo companyVo) {
        Company company=Company.convertFromVo(companyVo);
        boolean flag=companyService.save(company);
        if(flag){
            return companyVo;
        }
        return null;
    }
}
