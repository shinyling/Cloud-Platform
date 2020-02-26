package com.everwing.cloud.service.admin.mapper;

import com.everwing.cloud.service.platform.vo.CompanyVo;

/**
 * @author DELL
 * @title: SchemeMapper
 * @projectName cloud-platform
 * @description: TODO
 * @date 2020/2/26 9:39
 */
public interface SchemeMapper {

    /**
     * 添加數據源
     * @param companyVo 數據源名稱
     * @return 創建成功or失敗
     */
    void addScheme(CompanyVo companyVo);

    void createTables(CompanyVo companyVo);

    void insertData(CompanyVo companyVo);
}
