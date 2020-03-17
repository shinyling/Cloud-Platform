package com.everwing.cloud.service.platform.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author DELL shiny
 * @date 2020/3/17
 */
public enum PermissionTypeEnum {

    /**
     * 菜单权限
     */
    MENU(1, "菜单权限"),

    /**
     * 操作权限
     */
    OPERATION(2, "操作权限"),

    /**
     * 文件权限
     */
    FILE(3, "文件权限"),

    /**
     * 元素权限
     */
    ELEMENT(4, "元素权限");

    PermissionTypeEnum(
            final Integer code, final String desc) {
        this.code = code;
        this.desc = desc;
    }

    @EnumValue
    private final Integer code;

    @JsonValue
    private final String desc;

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
