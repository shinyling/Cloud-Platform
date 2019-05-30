package com.everwing.cloud.service.wy.datasource;

import com.everwing.cloud.service.platform.entity.Company;
import com.everwing.cloud.service.wy.remote.CompanyService;
import com.everwing.cloud.service.wy.utils.SpringBeanLocator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author DELL shiny
 * @create 2019/5/10
 */
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
    private RedisTemplate<String,Object> redisTemplate;

    //保存动态创建的数据源
    private static final Map<String,DataSource> targetDataSource = new HashMap<>();

    //主数据库初始化
    @PostConstruct
    private void init() {
        DataSource dataSource= (DataSource) DataSourceBuilder.create(Thread.currentThread().getContextClassLoader())
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
        List<Company> companyList= (List<Company>) redisTemplate.opsForValue().get("dataSource");
        if(companyList.isEmpty()){
            throw new RuntimeException("未查询到公司信息,初始化失败!");
        }
        Map<String,Company> companyMap=companyList.stream().collect(Collectors.toMap(Company::getCompanyId,a -> a,(k1,k2)->k1));
        Company database = companyMap.get(dbname);
        if(database==null){
            database=companyList.get(0);
        }
        // 切换回目标库
        oriSource=StringUtils.defaultIfBlank(oriSource,database.getCompanyId());
        DataBaseContextHolder.setCompanyId(oriSource);
        DataSource dataSource = (DataSource)DataSourceBuilder.create(Thread.currentThread().getContextClassLoader())
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

}
