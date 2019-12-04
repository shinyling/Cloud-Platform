package com.everwing.cloud.service.wy.datasource;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.everwing.cloud.service.platform.api.CompanyApi;
import com.everwing.cloud.service.platform.vo.Company;

import lombok.extern.slf4j.Slf4j;

/**
 * @author DELL shiny
 * @create 2019/5/10
 */
@Slf4j
@Component
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Value("${spring.datasource.driver-class-name}")
    private String driver;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    @Autowired
    private CompanyApi companyApi;

    //保存动态创建的数据源
    private static final Map<String,DataSource> targetDataSource = new HashMap<>();

    //主数据库初始化
    @PostConstruct
    private void init() {
        DataSource dataSource= DataSourceBuilder.create(Thread.currentThread().getContextClassLoader())
                .driverClassName(driver)
                .url(url)
                .username(username)
                .password(password).build();
        putDataSource("default", dataSource);
    }

    @Override
    protected DataSource determineTargetDataSource() {
        // 根据数据库选择方案，拿到要访问的数据库
        String dataSourceName = determineCurrentLookupKey();
        // 根据数据库名字，从已创建的数据库中获取要访问的数据库
        DataSource dataSource = targetDataSource.get(dataSourceName);
        if(null == dataSource) {
            //从已创建的数据库中获取要访问的数据库，如果没有则创建一个
            dataSource = this.selectDataSource(dataSourceName);
        }
        return dataSource;
    }

    @Override
    protected String determineCurrentLookupKey() {
        String dataSourceName = DataBaseContextHolder.getCompanyId();
        return dataSourceName;
    }

    /**
     * 该方法为同步方法，防止并发创建两个相同的数据库
     * @param dbname
     * @return
     */
    private synchronized DataSource selectDataSource(String dbname) {
        // 双重检查
        DataSource obj = DynamicDataSource.targetDataSource.get(dbname);
        if (null != obj) {
            return obj;
        }
        // 为空则创建数据库
        DataSource dataSource = this.setDataSource(dbname);
        if (null != dataSource) {
            // 将新创建的数据库保存到map中
            putDataSource(dbname, dataSource);
            return dataSource;
        }
        throw new RuntimeException("创建数据源失败！");
    }

    private void putDataSource(String dbname, DataSource dataSource) {
        DynamicDataSource.targetDataSource.put(dbname, dataSource);
    }

    /**
     * 查询对应数据库的信息
     * @param dbname
     * @return
     */
    private DataSource setDataSource(String dbname) {
        String oriSource = DataBaseContextHolder.getCompanyId();
        // 先切换回主库
        DataBaseContextHolder.setCompanyId(DataSourceUtil.DEFAULT);
        // 查询所需信息
        Company database = companyApi.query(dbname);
        // 切换回目标库
        oriSource=StringUtils.defaultIfBlank(oriSource,database.getCompanyId());
        DataBaseContextHolder.setCompanyId(oriSource);
        DataSource dataSource = DataSourceBuilder.create(Thread.currentThread().getContextClassLoader())
                .driverClassName(this.driver)
                .url(database.getJdbcUrl())
                .username(database.getJdbcUsername())
                .password(database.getJdbcPassword()).build();
        return dataSource;
    }

    @Override
    public void afterPropertiesSet() {
        //do nothing just for override. becauseof targetDataSource management by self.
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 开启 PageHelper 的支持
        return paginationInterceptor;
    }

    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        // TODO
        sqlSessionFactory.setDataSource(targetDataSource.get("default"));

        MybatisConfiguration configuration = new MybatisConfiguration();
        // 开启XML
        configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(false);
        sqlSessionFactory.setConfiguration(configuration);
        sqlSessionFactory.setPlugins(new Interceptor[]{ // PerformanceInterceptor(),OptimisticLockerInterceptor()
                paginationInterceptor() // 添加分页功能
        });
        return sqlSessionFactory.getObject();
    }

}
