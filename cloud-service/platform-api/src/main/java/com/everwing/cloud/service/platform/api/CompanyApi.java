package com.everwing.cloud.service.platform.api;

import com.everwing.cloud.service.platform.vo.CompanyVo;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author DELL shiny
 * @create 2019/5/9
 */
public interface CompanyApi {

    List<CompanyVo> list();

    CompanyVo query(@RequestParam("companyId") String companyId);

}
