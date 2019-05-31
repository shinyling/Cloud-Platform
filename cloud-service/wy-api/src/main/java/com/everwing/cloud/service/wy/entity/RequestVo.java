package com.everwing.cloud.service.wy.entity;

import lombok.Data;

/**
 * @author DELL shiny
 * @create 2019/5/31
 */
@Data
public class RequestVo<T> {

    private String username;

    private String companyId;

    private T t;
}
