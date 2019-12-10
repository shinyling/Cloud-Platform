package com.everwing.cloud.service.platform.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
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
public class CompanyVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String companyId;

    private String jdbcUrl;

    private String jdbcUsername;

    private String jdbcPassword;

    @NotNull(message="公司名称不能为空")
    private String companyName;

    private String companyLocation;

    @NotNull(message="公司地址不能为空")
    private String companyAddress;

    private String state;

    private String createBy;

    private LocalDateTime createTime;

    private String updateBy;

    private LocalDateTime updateTime;

}
