package com.everwing.cloud.common.entity;

import com.everwing.cloud.common.constant.ErrorCodeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author DELL shiny
 * @create 2019/5/17
 */
@Data
@NoArgsConstructor
public class ResultJson<T> {

    public static final String SUCCESS="200";

    public static final String FAIL="500";

    private String code;

    private String message;

    private T data;

    public ResultJson(ErrorCodeEnum errorCodeEnum){
        this.code=errorCodeEnum.getCode();
        this.message=errorCodeEnum.getMessage();
    }

    public ResultJson(T t){
        this.code=ErrorCodeEnum.SUCCESS.getCode();
        this.message=ErrorCodeEnum.SUCCESS.getMessage();
        this.data=t;
    }

    public ResultJson(ErrorCodeEnum codeEnum, String msg) {
        this.code=codeEnum.getCode();
        this.message=msg;
    }

    public static ResultJson success(Object t){
        return new ResultJson(t);
    }

    public static ResultJson fail(){
        return new ResultJson(ErrorCodeEnum.FAIL);
    }

    public static ResultJson fail(String msg){
        return new ResultJson(ErrorCodeEnum.FAIL,msg);
    }


    public static ResultJson noContent(){
        return new ResultJson(ErrorCodeEnum.NO_CONTENT);
    }

}
