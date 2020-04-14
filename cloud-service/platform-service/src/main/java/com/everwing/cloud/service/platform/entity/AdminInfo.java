package com.everwing.cloud.service.platform.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * admin额外信息表
 * </p>
 *
 * @author shiny
 * @since 2020-04-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("admin_info")
public class AdminInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String adminInfoId;

    /**
     * 工号
     */
    private String workNum;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 0/false=女 1/true=男
     */
    private Boolean sex;

    /**
     * 创建人account_id
     */
    private String createAccountId;

    private String accountId;


}
