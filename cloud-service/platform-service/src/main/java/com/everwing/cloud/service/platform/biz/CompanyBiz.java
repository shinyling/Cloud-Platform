package com.everwing.cloud.service.platform.biz;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.everwing.cloud.common.exception.BusinessException;
import com.everwing.cloud.service.platform.constant.CompanyStateEnum;
import com.everwing.cloud.service.platform.entity.Company;
import com.everwing.cloud.service.platform.msg.SendMsg;
import com.everwing.cloud.service.platform.service.ICompanyService;
import com.everwing.cloud.service.platform.vo.CompanyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
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

    @Value("${spring.datasource.url}")
    private String url;

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
        company.setState(CompanyStateEnum.WATING_AUDIT.getState());
        String username=RandomUtil.randomString(6);
        String password=RandomUtil.randomString(8).concat("*").concat(RandomUtil.randomStringUpper(2));
        company.setJdbcUsername(username);
        company.setJdbcPassword(password);
        Snowflake snowflake=new Snowflake(2,5);
        String dbName="tenant_".concat(String.valueOf(snowflake.nextId()));
        String jdbcUrl=url.replace("ucenter",dbName);
        company.setJdbcUrl(jdbcUrl);
        boolean flag=companyService.save(company);
        if(flag){
            return companyVo;
        }
        throw new BusinessException("创建公司失败!");
    }

    public void audit(CompanyVo companyVo) throws BusinessException {
        Company company=Company.convertFromVo(companyVo);
        //审核成功
        if(company.getState().equals(CompanyStateEnum.AUDIT_SUCCESS.getState())) {
            boolean flag=companyService.updateById(company);
            if(!flag){
                throw new BusinessException("审核公司失败!");
            }
            company=companyService.getById(company.getCompanyId());
            //todo 校验是否重发
            sendMsg.sendMsg(JSON.toJSONString(company));
        //审核失败
        }else if(company.getState().equals(CompanyStateEnum.AUDIT_FAIL.getState())){
        //状态待审核
        }else {
            throw new BusinessException("公司状态错误，操作失败!");
        }
    }
}
