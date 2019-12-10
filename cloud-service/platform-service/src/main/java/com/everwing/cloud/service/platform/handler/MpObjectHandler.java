package com.everwing.cloud.service.platform.handler;

import java.time.LocalDateTime;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.everwing.cloud.common.vo.UserVo;

import lombok.extern.slf4j.Slf4j;

/**
 * @author DELL shiny
 * @create 2019/12/6
 */
@Slf4j
@Component
public class MpObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        UserVo userVo = JSON.parseObject(JSON.toJSONString(SecurityContextHolder.getContext().getAuthentication().getPrincipal()),UserVo.class);
        this.setFieldValByName("createBy",userVo.getId(),metaObject);
        this.setFieldValByName("createTime", LocalDateTime.now(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        UserVo userVo = JSON.parseObject(JSON.toJSONString(SecurityContextHolder.getContext().getAuthentication().getPrincipal()),UserVo.class);
        this.setFieldValByName("updateBy", userVo.getId(),metaObject);
        this.setFieldValByName("updateTime", LocalDateTime.now(),metaObject);
    }
}
