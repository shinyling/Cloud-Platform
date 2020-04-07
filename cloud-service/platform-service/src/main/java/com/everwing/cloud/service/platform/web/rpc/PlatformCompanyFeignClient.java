package com.everwing.cloud.service.platform.web.rpc;

import com.alibaba.dubbo.config.annotation.Service;
import com.everwing.cloud.service.platform.api.CompanyApi;
import com.everwing.cloud.service.platform.biz.CompanyBiz;
import com.everwing.cloud.service.platform.vo.CompanyVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author DELL shiny
 * @create 2019/10/16
 */
@Service
public class PlatformCompanyFeignClient implements CompanyApi {

    @Autowired
    private CompanyBiz companyBiz;

    @Override
    public List<CompanyVo> list() {
        return companyBiz.listAll();
    }

    @Override
    public CompanyVo query(String companyId) {
        return companyBiz.selectById(companyId);
    }

}
