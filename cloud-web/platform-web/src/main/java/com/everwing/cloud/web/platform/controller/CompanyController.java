package com.everwing.cloud.web.platform.controller;


import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.web.platform.service.CompanyService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author shiny
 * @since 2019-05-07
 */
@RestController
@RequestMapping("/company")
@Api(value = "Company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/list")
    public ResultJson listCompany(){
        return ResultJson.success(companyService.list());
    }

}
