package com.everwing.cloud.service.admin.mapper;

import com.everwing.cloud.service.platform.vo.CompanyVo;

/**
 * @author DELL
 * @title: SchemeMapper
 * @projectName cloud-platform
 * @description: 公司创建初始化
 * @date 2020/2/26 9:39
 */
public interface SchemeMapper {

    /**
     * 添加數據源
     * @param companyVo 數據源名稱
     * @return 創建成功or失敗
     */
    void addScheme(CompanyVo companyVo);

    /**
     * 建表
     * @param companyVo
     */
    void createTables(CompanyVo companyVo);

    /**
     * 初始化数据
     * @param companyVo
     */
    void insertData(CompanyVo companyVo);
}
