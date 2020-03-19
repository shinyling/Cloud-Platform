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
 * @since 2020-03-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("operation")
public class Operation implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    @NotBlank(message = "操作名称不能为空", groups = AddGroup.class)
    private String name;

    private String code;

    private String urlPrefix;


}
