package com.everwing.cloud.service.platform.entity;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.annotation.TableName;
import com.everwing.cloud.service.platform.vo.CompanyVo;
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
@TableName("company")
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;

    private String companyId;

    private String jdbcUrl;

    private String jdbcUsername;

    private String jdbcPassword;

    /**
     * 企业全称
     */
    private String companyName;

    /**
     * 企业省市区
     */
    private String companyLocation;

    /**
     * 企业详细地址
     */
    private String companyAddress;

    /**
     * 工商执照注册号
     */
    private String bizRegistryLicenseNum;

    /**
     * 企业Logo
     */
    private String logoFileId;

    /**
     * 企业工商营业执照
     */
    private String bizSaleLicenseFileId;

    /**
     * 组织机构代码证
     */
    private String orgCodeFileId;

    /**
     * 税务登记证
     */
    private String taxLicenseFileId;

    /**
     * 物业资质证书，非必填
     */
    private String propertyCertFileId;

    /**
     * 0=不通过 1=通过 2=审核中
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    public static CompanyVo convertToVo(Company company) {
        CompanyVo companyVo = new CompanyVo();
        BeanUtil.copyProperties(company, companyVo);
        return companyVo;
    }

    public static Company convertFromVo(CompanyVo companyVo) {
        Company company = new Company();
        BeanUtil.copyProperties(companyVo, company);
        return company;
    }
}
