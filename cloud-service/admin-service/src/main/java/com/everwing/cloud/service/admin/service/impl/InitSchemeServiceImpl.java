package com.everwing.cloud.service.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everwing.cloud.service.admin.mapper.SchemeMapper;
import com.everwing.cloud.service.admin.service.InitSchemeService;
import com.everwing.cloud.service.platform.vo.CompanyVo;

import lombok.extern.slf4j.Slf4j;

/**
 * @author DELL shiny
 * @create 2020/2/26
 */
@Slf4j
@Service
public class InitSchemeServiceImpl implements InitSchemeService {

    @Autowired
    private SchemeMapper schemeMapper;

    @Override
    public void init(CompanyVo companyVo) {
        String jdbc=companyVo.getJdbcUrl();
        companyVo.setSchemeName(jdbc.substring(jdbc.lastIndexOf("/")+1,jdbc.indexOf("?")));
        schemeMapper.addScheme(companyVo);
        log.info("創建數據庫完成！数据库名称[{}]",companyVo.getSchemeName());
        createTables(companyVo);
        initData(companyVo);
    }

    private void createTables(CompanyVo companyVo){
        schemeMapper.createTables(companyVo);
        log.info("数据库:[{}]创建表完成！",companyVo.getSchemeName());
    }

    private void initData(CompanyVo companyVo){
        schemeMapper.insertData(companyVo);
        log.info("数据库:[{}]初始化数据完成!",companyVo.getSchemeName());
    }
}
