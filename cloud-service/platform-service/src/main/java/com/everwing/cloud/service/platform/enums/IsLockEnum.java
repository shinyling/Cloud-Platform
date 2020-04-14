package com.everwing.cloud.service.platform.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author DELL shiny
 * @date 2020/4/13
 */
public enum IsLockEnum {

    LOCKED(true, "已锁定"),

    UN_LOCKED(false, "未锁定");

    IsLockEnum(Boolean code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @EnumValue
    private final Boolean code;

    @JsonValue
    private String msg;

    public Boolean getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
