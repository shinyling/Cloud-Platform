package com.everwing.cloud.service.wy;

import com.everwing.cloud.service.platform.api.CompanyApi;
import com.everwing.cloud.service.platform.vo.Company;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

/**
 * @author DELL shiny
 * @create 2019/5/10
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.everwing.cloud.service.wy.mapper")
@EnableFeignClients
public class WyServiceApplication {

    private CompanyApi companyApi;

    private RedisTemplate<String,Object> redisTemplate;

    public static void main(String[] args) {
        SpringApplication.run(WyServiceApplication.class,args);
    }

    @Autowired
    private void setDataSource(CompanyApi companyApi,RedisTemplate<String,Object> redisTemplate){
        List<Company> companyList=companyApi.list();
        redisTemplate.opsForValue().set("dataSource",companyList);
    }


}
