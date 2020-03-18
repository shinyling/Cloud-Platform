package com.everwing.cloud.service.platform.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.everwing.cloud.service.platform.group.AddGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
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
@TableName("element")
public class Element implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    @NotNull(message = "名称不能为空", groups = AddGroup.class)
    private String name;

    private String code;


}
