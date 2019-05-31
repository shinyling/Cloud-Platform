package com.everwing.cloud.common.entity;

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
 * @since 2019-05-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String mobile;

    private String password;

    private Boolean status;

    private Boolean isLocked;

    private Boolean isDelete;

    private LocalDateTime createTime;

    private String createBy;

    private LocalDateTime updateTime;

    private String updateBy;

    private Integer isLock;

    private String companyId;

    private Boolean type;

}
