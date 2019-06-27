package com.everwing.cloud.service.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.everwing.cloud.service.platform.entity.Company;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shiny
 * @since 2019-05-07
 */
public interface ICompanyService extends IService<Company> {

    Company createSchema(Company company);
}
