package com.everwing.cloud.service.platform.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.everwing.cloud.service.platform.enums.PermissionTypeEnum;
import com.everwing.cloud.service.platform.group.AddGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

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
@TableName("permission")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    @NotBlank(message = "请输入权限名称", groups = AddGroup.class)
    private String name;

    private PermissionTypeEnum type;

    @NotBlank(message = "请输入权限标识", groups = AddGroup.class)
    private String permissionVal;

    private String uri;

    private String icon;

    @NotBlank(message = "请输入权限描述", groups = AddGroup.class)
    private String permissionDesc;

    private String pid;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT)
    private String createBy;
}
