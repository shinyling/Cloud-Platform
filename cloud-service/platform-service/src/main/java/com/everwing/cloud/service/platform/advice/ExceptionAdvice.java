package com.everwing.cloud.service.platform.advice;

import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
    @ExceptionHandler(Exception.class)
    public ResultJson exception(Exception e) {
        log.error(e.getMessage(), e);
        return ResultJson.fail();
    }
}
