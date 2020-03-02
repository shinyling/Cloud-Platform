package com.everwing.cloud.service.platform.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;

import com.everwing.cloud.service.platform.vo.CompanyVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

/**
 * <p>
 * 
 * </p>
 *
 * @author shiny
 * @since 2019-12-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("company")
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ID_WORKER_STR)
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

    private String state;

    /**
     * 企业详细地址
     */
    private String companyAddress;

    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.UPDATE)
    private String updateBy;

    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    public static Company convertFromVo(CompanyVo companyVo){
        Company company=new Company();
        BeanUtils.copyProperties(companyVo,company);
        return company;
    }

    public static CompanyVo convertToVo(Company company){
        if(null==company){
            return null;
        }
        CompanyVo companyVo=new CompanyVo();
        BeanUtils.copyProperties(company,companyVo);
        return companyVo;
    }
}
