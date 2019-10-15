package com.everwing.cloud.common.constant;

/**
 * @author DELL shiny
 * @create 2019/5/31
 */
public enum ErrorCodeEnum {
    /**
     * 请求成功
     */
    SUCCESS("200","请求成功"),

    /**
     * 请求失败
     */
    FAIL("500","系统错误,请求失败！"),

    /**
     * 请先登录
     */
    NOT_AUTHORIZE("401","请先登录!"),

    /**
     * 未查询到符合条件的结果
     */
    NO_CONTENT("200","未查询到符合条件的结果!");

    private String code;

    private String message;

    ErrorCodeEnum(String code,String message){
        this.code=code;
        this.message=message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
