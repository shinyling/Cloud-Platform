package com.everwing.cloud.common.entity;

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
 * @since 2019-05-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    private String accountId;

    private String accountName;

    private String password;

    private Integer type;

    private String accountCode;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer state;

    private String exData;

    private String companyId;

    private String sourceId;

    private Boolean superAdmin;

    private String sourceType;

    private String mobile;

}
