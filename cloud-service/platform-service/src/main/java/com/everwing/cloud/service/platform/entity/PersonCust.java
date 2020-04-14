package com.everwing.cloud.service.platform.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@TableName("person_cust")
public class PersonCust implements Serializable {

    private static final long serialVersionUID = 1L;

    private String custId;

    private String name;

    private String sex;

    /**
     * 证件类型
     */
    private Integer cardType;

    /**
     * 证件号码
     */
    private String cardNum;

    /**
     * 紧急联系人
     */
    private String urgentContactPerson;

    /**
     * 紧急联系电话
     */
    private String urgentContactPhone;

    private String phoneNum;

    private String registerPhone;

    private String companyId;

    private String projectId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 国籍
     */
    private String national;

    /**
     * 籍贯
     */
    private String nativePlace;


}
