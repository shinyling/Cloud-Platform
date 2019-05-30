package com.everwing.cloud.service.wy.config;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.everwing.cloud.service.wy.datasource.DynamicDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author DELL shiny
 * @create 2019/5/30
 */
@EnableTransactionManagement
@Configuration
public class MybatisPlusConfig {

    @Autowired
    private DynamicDataSource dataSource;

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }

    @Bean
    @Profile({"dev","qa"})// 设置 dev test 环境开启
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        performanceInterceptor.setMaxTime(1000);
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }

    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/com/everwing/cloud/service/wy/mapper/*/*Mapper.xml"));

        MybatisConfiguration configuration = new MybatisConfiguration();
        //configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(false);
        sqlSessionFactory.setConfiguration(configuration);
        sqlSessionFactory.setPlugins(new Interceptor[]{ performanceInterceptor(),/*OptimisticLockerInterceptor(),*/
                paginationInterceptor() //添加分页功能
        });
//        sqlSessionFactory.setGlobalConfig(globalConfiguration());
        return sqlSessionFactory.getObject();
    }

//    @Bean
//    public GlobalConfiguration globalConfiguration() {
//        GlobalConfiguration conf = new GlobalConfiguration(new LogicSqlInjector());
//        conf.setLogicDeleteValue("-1");
//        conf.setLogicNotDeleteValue("1");
//        conf.setIdType(0);
//        //conf.setMetaObjectHandler(new MyMetaObjectHandler());
//        conf.setDbColumnUnderline(true);
//        conf.setRefresh(true);
//        return conf;
//    }
}
