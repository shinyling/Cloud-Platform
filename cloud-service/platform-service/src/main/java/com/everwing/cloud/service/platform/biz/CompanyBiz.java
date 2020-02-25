package com.everwing.cloud.service.platform.biz;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.everwing.cloud.common.exception.BusinessException;
import com.everwing.cloud.service.platform.entity.Company;
import com.everwing.cloud.service.platform.msg.SendMsg;
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

    @Autowired
    private SendMsg sendMsg;

    public List<CompanyVo> listAll(){
        return companyService.list().stream().map(company -> company.convertToVo()).collect(Collectors.toList());
    }

    public CompanyVo selectById(String companyId) {
        return companyService.getById(companyId).convertToVo();
    }

    public CompanyVo add(CompanyVo companyVo) throws BusinessException {
        Company company=Company.convertFromVo(companyVo);
        QueryWrapper<Company> companyWrapper=new QueryWrapper();
        companyWrapper.eq("company_name",company.getCompanyName());
        Company exists=companyService.getOne(companyWrapper);
        if(exists!=null){
            throw new BusinessException("公司名称已存在!");
        }
        //状态待审核
        company.setState("0");
        boolean flag=companyService.save(company);
        if(flag){
            return companyVo;
        }
        throw new BusinessException("创建公司失败!");
    }

    public void audit(CompanyVo companyVo) throws BusinessException {
        Company company=Company.convertFromVo(companyVo);
        boolean flag=companyService.updateById(company);
        if(!flag){
            throw new BusinessException("审核公司失败!");
        }
        company=companyService.getById(company.getCompanyId());
        sendMsg.sendMsg(JSON.toJSONString(company));
    }
}
