package com.everwing.cloud.service.platform.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author shiny
 * @since 2019-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;

    private String companyId;

    private String jdbcUrl;

    private String jdbcUsername;

    private String jdbcPassword;

    private String companyName;

    private String companyLocation;

    private String companyAddress;

    private String state;

    private String createBy;

    private LocalDateTime createTime;

    private String updateBy;

    private LocalDateTime updateTime;
}
