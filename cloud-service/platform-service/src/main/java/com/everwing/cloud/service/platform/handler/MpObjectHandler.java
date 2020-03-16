package com.everwing.cloud.service.platform.handler;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.everwing.cloud.common.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author DELL shiny
 * @create 2019/12/6
 */
@Slf4j
@Component
public class MpObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        String userString = JSON.toJSONString(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        if (userString.startsWith("{")) {
            UserVo userVo = JSON.parseObject(userString, UserVo.class);
            checkAndSet("createBy", userVo.getUsername(), metaObject);
        }
        checkAndSet("createTime", LocalDateTime.now(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        String userString = JSON.toJSONString(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        if (userString.startsWith("{")) {
            UserVo userVo = JSON.parseObject(userString, UserVo.class);
            checkAndSet("updateBy", userVo.getId(), metaObject);
        }
        checkAndSet("updateTime", LocalDateTime.now(), metaObject);
    }

    private void checkAndSet(String fieldName, Object value, MetaObject source) {
        if (source.hasSetter(fieldName)) {
            setFieldValByName(fieldName, value, source);
        }
    }
}
