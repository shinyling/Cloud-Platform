package com.everwing.cloud.service.platform.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.everwing.cloud.service.platform.group.AddGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author shiny
 * @since 2019-12-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("permission_menu")
public class PermissionMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "请选择权限", groups = AddGroup.class)
    private String permissionId;

    @NotBlank(message = "请选择菜单", groups = AddGroup.class)
    private String menuId;

}
