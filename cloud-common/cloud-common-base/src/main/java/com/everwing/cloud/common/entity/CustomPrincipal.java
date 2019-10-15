package com.everwing.cloud.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author DELL shiny
 * @create 2019/5/30
 */
@Data
public class CustomPrincipal implements Serializable {

    private String id;

    private String username;

    private String companyId;

}
