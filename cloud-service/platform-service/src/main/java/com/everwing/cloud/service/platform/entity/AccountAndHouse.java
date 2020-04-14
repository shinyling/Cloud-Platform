package com.everwing.cloud.service.platform.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.everwing.cloud.service.platform.group.AddGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * <p>
 * 账号和房屋绑定关系表
 * </p>
 *
 * @author shiny
 * @since 2020-04-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("account_and_house")
public class AccountAndHouse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 邻音账号Mobile
     */
    @NotBlank(message = "手机号不能为空", groups = AddGroup.class)
    @Pattern(regexp = "(?:0|86|\\+86)?1[3456789]\\d{9}")
    private String mobile;

    /**
     * 房屋编码
     */
    @NotBlank(message = "房屋编码不能为空", groups = AddGroup.class)
    private String houseCode;


}
