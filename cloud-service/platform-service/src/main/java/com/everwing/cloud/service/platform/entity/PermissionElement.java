package com.everwing.cloud.service.platform.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
@TableName("permission_element")
public class PermissionElement implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "请选择权限")
    private String permissionId;

    @NotBlank(message = "请选择元素")
    private String elementId;


}
