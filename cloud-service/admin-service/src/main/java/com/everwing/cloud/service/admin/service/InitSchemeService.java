package com.everwing.cloud.service.admin.service;

import com.everwing.cloud.service.platform.vo.CompanyVo;

/**
 * @author DELL
 * @title: InitScheme
 * @projectName cloud-platform
 * @description: TODO
 * @date 2020/2/269:36
 */
public interface InitSchemeService {
    /**
     * 根據消息初始化數據庫
     * @param companyVo
     */
    void init(CompanyVo companyVo);
}
