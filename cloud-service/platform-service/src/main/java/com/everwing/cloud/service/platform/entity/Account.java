package com.everwing.cloud.service.platform.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author shiny
 * @since 2020-04-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("account")
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    private String accountId;

    /**
     * 来源账号
     */
    private String accountName;

    private String password;

    private Integer type;

    /**
     * 内部账号
     */
    private String accountCode;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    /**
     * 0=注销 1=正常
     */
    private Integer state;

    private String exData;

    private String companyId;

    private String sourceId;

    private Boolean superAdmin;

    /**
     * pc ios  android
     */
    private String sourceType;

    private String mobile;


}
