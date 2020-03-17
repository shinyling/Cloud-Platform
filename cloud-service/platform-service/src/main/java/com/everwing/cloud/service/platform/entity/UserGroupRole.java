package com.everwing.cloud.service.platform.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.everwing.cloud.service.platform.group.AddGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

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
@TableName("user_group_role")
public class UserGroupRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "组不能为空", groups = AddGroup.class)
    private String groupId;

    @NotBlank(message = "角色不能为空", groups = AddGroup.class)
    private String roleId;

}
