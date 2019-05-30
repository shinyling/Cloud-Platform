package com.everwing.cloud.service.wy.utils;

import com.alibaba.fastjson.JSON;
import com.everwing.cloud.service.platform.entity.Company;
import com.everwing.cloud.service.wy.remote.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author DELL shiny
 * @create 2019/5/29
 */
@Slf4j
@Component
public class InitialData implements CommandLineRunner {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public void run(String... args) throws Exception {
        List<Company> companyList=companyService.list();
        redisTemplate.opsForValue().set("dataSource",companyList);
    }
}
