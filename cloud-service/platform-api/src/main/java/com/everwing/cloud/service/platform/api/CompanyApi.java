package com.everwing.cloud.service.platform.api;

import com.everwing.cloud.service.platform.entity.Company;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author DELL shiny
 * @create 2019/5/9
 */
@RequestMapping("platform-api-company")
public interface CompanyApi {

    @GetMapping("list")
    List<Company> list();
}
