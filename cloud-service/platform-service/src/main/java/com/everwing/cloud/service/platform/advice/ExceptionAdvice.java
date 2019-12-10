package com.everwing.cloud.service.platform.advice;

import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author DELL shiny
 * @create 2019/12/5
 */
@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    public ResultJson businessException(BusinessException e) {
        log.error(e.getMessage(), e);
        return ResultJson.fail(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(BindException.class)
    public ResultJson businessException(BindException e) {
        List<FieldError> fieldErrors = e.getFieldErrors();
        StringBuilder stringBuilder=new StringBuilder();
        for(FieldError error:fieldErrors){
            stringBuilder.append(error.getDefaultMessage()).append(";");
        }
        String msg=stringBuilder.substring(0,stringBuilder.length()-1);
        log.error("校验失败:[{}]", msg);
        return ResultJson.fail(msg);
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResultJson exception(Exception e) {
        log.error(e.getMessage(), e);
        return ResultJson.fail();
    }
}
