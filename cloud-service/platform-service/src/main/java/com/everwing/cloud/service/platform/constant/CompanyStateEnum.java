package com.everwing.cloud.service.platform.constant;

/**
 * @author DELL
 * @title: CompanyStateEnum
 * @projectName cloud-platform
 * @description: TODO
 * @date 2020/2/2610:47
 */
public enum CompanyStateEnum {

    WATING_AUDIT("2","待审核"),

    AUDIT_SUCCESS("1","审核成功"),

    AUDIT_FAIL("0","审核失败");

    private String state;

    private String msg;

    CompanyStateEnum(String state,String msg){
        this.state=state;
        this.msg=msg;
    }

    public String getState() {
        return state;
    }

    public String getMsg() {
        return msg;
    }
}
