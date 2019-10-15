package com.everwing.cloud.service.platform.api;

import com.everwing.cloud.service.platform.vo.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author DELL shiny
 * @create 2019/5/9
 */
@FeignClient("cloud-provider-platform")
public interface CompanyApi {

    @GetMapping("list")
    List<Company> list();

    @GetMapping("query")
    Company query(String companyId);

    @PostMapping("insert")
    Company insert(Company company);

}
