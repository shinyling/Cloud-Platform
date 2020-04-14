package com.everwing.cloud.service.platform.enums;

/**
 * @author DELL
 * @title: CompanyStateEnum
 * @projectName cloud-platform
 * @description: TODO
 * @date 2020/2/2610:47
 */
public enum CompanyStatusEnum {

    WAITING_AUDIT(2, "待审核"),

    AUDIT_SUCCESS(1, "审核成功"),

    AUDIT_FAIL(0, "审核失败");

    private Integer status;

    private String msg;

    CompanyStatusEnum(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}
