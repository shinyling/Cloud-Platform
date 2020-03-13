package com.everwing.cloud.service.platform.advice;

import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

/**
 * @author DELL shiny
 * @create 2019/12/5
 */
@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    /**
     * 处理自定义的业务异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public ResultJson bizExceptionHandler(BusinessException e) {
        log.error("发生业务异常！原因是：{}", e.getMessage());
        return ResultJson.fail(e.getMessage());
    }

    /**
     * 处理空指针的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public ResultJson exceptionHandler(NullPointerException e) {
        log.error("发生空指针异常！原因是:", e);
        return ResultJson.fail("空指针异常！");
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResultJson exceptionHandler(MethodArgumentNotValidException e) {
        log.error("校验不通过！原因是:", e.getMessage());
        return ResultJson.fail(e.getBindingResult().getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).collect(Collectors.joining(";")));
    }


    /**
     * 处理其他异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultJson exceptionHandler(Exception e) {
        log.error("未知异常！原因是:", e);
        return ResultJson.fail("系统内部错误!");
    }
}
