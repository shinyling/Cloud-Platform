package com.everwing.cloud.service.platform.web.rpc;

import com.everwing.cloud.service.platform.api.CompanyApi;
import com.everwing.cloud.service.platform.biz.CompanyBiz;
import com.everwing.cloud.service.platform.vo.CompanyVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author DELL shiny
 * @create 2019/10/16
 */
@RefreshScope
@RestController
@RequestMapping("rpc/company")
public class PlatformCompanyFeignClient implements CompanyApi {

    @Autowired
    private CompanyBiz companyBiz;

    @Override
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public List<CompanyVo> list() {
        return companyBiz.listAll();
    }

    @Override
    @RequestMapping(value = "query",method = RequestMethod.GET)
    public CompanyVo query(String companyId) {
        return companyBiz.selectById(companyId);
    }

}
