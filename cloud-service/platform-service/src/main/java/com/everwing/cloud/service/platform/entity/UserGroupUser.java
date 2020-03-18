package com.everwing.cloud.service.platform.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.everwing.cloud.service.platform.group.AddGroup;
import com.everwing.cloud.service.platform.group.LoadGroup;
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
 * @since 2020-03-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_group_user")
public class UserGroupUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "组不能为空", groups = {AddGroup.class, LoadGroup.class})
    private String groupId;

    @NotBlank(message = "用户不能为空", groups = AddGroup.class)
    private String userId;


}
