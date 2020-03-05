package com.everwing.cloud.service.platform.api;

import com.everwing.cloud.service.platform.vo.CompanyVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author DELL shiny
 * @create 2019/5/9
 */
@FeignClient("platform")
public interface CompanyApi {

    @RequestMapping(value = "/rpc/company/list",method = RequestMethod.GET)
    List<CompanyVo> list();

    @RequestMapping(value = "/rpc/company/query",method = RequestMethod.GET)
    CompanyVo query(@RequestParam("companyId") String companyId);

}
