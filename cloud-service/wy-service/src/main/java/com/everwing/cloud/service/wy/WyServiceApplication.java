package com.everwing.cloud.service.wy;

import com.everwing.cloud.service.platform.entity.Company;
import com.everwing.cloud.service.wy.remote.AccountService;
import com.everwing.cloud.service.wy.remote.CompanyService;
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

    private CompanyService companyService;

    private RedisTemplate<String,Object> redisTemplate;

    public static void main(String[] args) {
        SpringApplication.run(WyServiceApplication.class,args);
    }

    @Autowired
    private void setDataSource(CompanyService companyService,RedisTemplate<String,Object> redisTemplate){
        List<Company> companyList=companyService.list();
        redisTemplate.opsForValue().set("dataSource",companyList);
    }


}
