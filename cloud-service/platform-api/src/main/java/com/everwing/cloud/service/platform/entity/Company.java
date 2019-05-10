package com.everwing.cloud.service.platform.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

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

    private String code;

    private String jdbcUrl;

    private String jdbcUsername;

    private String jdbcPassword;

    private String companyName;

    private String companyLocation;

    private String companyAddress;

    private String bizRegistryLicenseNum;

    private String logoFileId;

    private String bizSaleLicenseFileId;

    private String orgCodeFileId;

    private String taxLicenseFileId;

    private String propertyCertFileId;

}
