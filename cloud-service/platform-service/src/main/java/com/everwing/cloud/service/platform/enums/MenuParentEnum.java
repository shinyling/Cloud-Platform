package com.everwing.cloud.service.platform.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author DELL
 * @title: MenuParentEnum
 * @projectName cloud-platform
 * @description: TODO
 * @date 2020/4/158:30
 */
public enum MenuParentEnum {

    PARENT(1, "父节点"),

    NOT_PARENT(0, "非父节点");

    @EnumValue
    private Integer code;

    @JsonValue
    private String msg;

    MenuParentEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public Integer getCode() {
        return code;
    }
}
