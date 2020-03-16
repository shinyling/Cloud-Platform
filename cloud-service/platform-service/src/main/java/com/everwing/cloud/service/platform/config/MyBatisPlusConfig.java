package com.everwing.cloud.service.platform.config;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.everwing.cloud.service.platform.handler.MpObjectHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author DELL shiny
 * @create 2020/3/5
 */
@Configuration
public class MyBatisPlusConfig {

    @Autowired
    private DataSource dataSource;

    /**
     * mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }

    @Bean
    public GlobalConfig globalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        GlobalConfig.DbConfig dbConfig = new GlobalConfig.DbConfig();
        dbConfig.setLogicDeleteValue("true");
        dbConfig.setLogicNotDeleteValue("false");
        dbConfig.setIdType(IdType.ID_WORKER_STR);
        globalConfig.setDbConfig(dbConfig);
        globalConfig.setSqlInjector(new LogicSqlInjector());
        globalConfig.setMetaObjectHandler(new MpObjectHandler());
        return globalConfig;
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        String mapperLocations = "classpath:com/everwing/cloud/service/platform/mapper/xml/*Mapper.xml";
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactory.setMapperLocations(resolver.getResources(mapperLocations));
        sqlSessionFactory.setGlobalConfig(globalConfig());
        sqlSessionFactory.setPlugins(new Interceptor[]{paginationInterceptor()});
        return sqlSessionFactory.getObject();
    }

}