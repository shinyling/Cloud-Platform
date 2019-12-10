package com.everwing.cloud.service.platform.web.rpc;

import com.everwing.cloud.service.platform.api.CompanyApi;
import com.everwing.cloud.service.platform.biz.CompanyBiz;
import com.everwing.cloud.service.platform.vo.CompanyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author DELL shiny
 * @create 2019/10/16
 */
@RefreshScope
@RestController
@RequestMapping("company")
public class PlatformCompanyFeignClient implements CompanyApi {

    @Autowired
    private CompanyBiz companyBiz;

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

    @Override
    @PostMapping("insert")
    public CompanyVo insert(@RequestBody CompanyVo company) {
        return companyBiz.add(company);
    }
}
