package com.everwing.cloud.service.wy.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.everwing.cloud.service.platform.entity.Company;
import com.everwing.cloud.service.wy.remote.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author DELL shiny
 * @create 2019/5/10
 */
@Configuration
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DataBaseContextHolder.getCompanyId();
    }

    public DynamicDataSource(CompanyService companyService){
        List<Company> companyList=companyService.list();
        Map<Object,Object> dataSourceMap=new HashMap<>(companyList.size());
        for(int i=0;i<companyList.size();i++){
            Company company=companyList.get(i);
            if(i==0){
                super.setDefaultTargetDataSource(mysqlDataSource(company));
            }
            dataSourceMap.put(company.getCompanyId(),mysqlDataSource(company));
        }
        super.setTargetDataSources(dataSourceMap);
    }

    public DataSource mysqlDataSource(Company company){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(company.getJdbcUrl());
        dataSource.setUsername(company.getJdbcUsername());
        dataSource.setPassword(company.getJdbcPassword());
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setInitialSize(2);
        // 从池中取得链接时做健康检查，该做法十分保守
        dataSource.setTestOnBorrow(true);
        // 如果连接空闲超过1小时就断开
        dataSource.setMinEvictableIdleTimeMillis(1 * 60000 * 60);
        // 每十分钟验证一下连接
        dataSource.setTimeBetweenEvictionRunsMillis(600000);
        // 运行ilde链接测试线程，剔除不可用的链接
        dataSource.setTestWhileIdle(true);
        dataSource.setMaxWait(-1);
        return dataSource;
    }
}
