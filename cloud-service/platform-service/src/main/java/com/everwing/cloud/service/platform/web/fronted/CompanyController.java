package com.everwing.cloud.service.platform.web.fronted;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.service.platform.biz.CompanyBiz;
import com.everwing.cloud.service.platform.vo.CompanyVo;

import cn.hutool.core.lang.Assert;

/**
 * @author DELL shiny
 * @create 2019/12/10
 */
@RestController
@RequestMapping("company")
public class CompanyController {

    @Autowired
    private CompanyBiz companyBiz;

    @PostMapping("register")
    public ResultJson register(@Validated CompanyVo companyVo){
        companyVo=companyBiz.add(companyVo);
        Assert.notNull(companyVo,"新公司注册失败!");
        return ResultJson.success(companyVo);
    }

}
