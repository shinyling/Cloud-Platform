package com.everwing.cloud.service.platform.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.everwing.cloud.service.platform.enums.MenuParentEnum;
import com.everwing.cloud.service.platform.group.AddGroup;
import com.everwing.cloud.service.platform.group.UpdateGroup;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
@TableName("menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;
    @NotBlank(message = "id不能为空", groups = UpdateGroup.class)
    private String id;

    @NotBlank(message = "菜单名称不能为空", groups = AddGroup.class)
    private String name;

    @NotBlank(message = "请求路径不能为空", groups = AddGroup.class)
    private String url;

    private String pid;

    private String icon;

    @NotNull(message = "菜单级数不能为空", groups = AddGroup.class)
    private Integer level;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    private MenuParentEnum parent;
}
