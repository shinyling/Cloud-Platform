package com.everwing.cloud.common.entity;

import lombok.Data;

/**
 * @author DELL shiny
 * @create 2019/5/17
 */
@Data
public class ResultJson<T> {

    private String code;

    private String message;

    private T data;
}
