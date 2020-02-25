package com.everwing.cloud.service.platform.web.rpc;

import com.everwing.cloud.service.platform.api.CompanyApi;
import com.everwing.cloud.service.platform.biz.CompanyBiz;
import com.everwing.cloud.service.platform.vo.CompanyVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author DELL shiny
 * @create 2019/10/16
 */
@Api("公司远程调用controller")
@RefreshScope
@RestController
@RequestMapping("company")
public class PlatformCompanyFeignClient implements CompanyApi {

    @Autowired
    private CompanyBiz companyBiz;

    @ApiOperation("列出所有公司")
    @Override
    @GetMapping("list")
    public List<CompanyVo> list() {
        return companyBiz.listAll();
    }

    @Override
    @GetMapping("query")
    public CompanyVo query(String companyId) {
        return companyBiz.selectById(companyId);
    }

}
