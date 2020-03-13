package com.everwing.cloud.service.platform.vo;

import cn.hutool.core.bean.BeanUtil;
import com.everwing.cloud.service.platform.entity.User;
import com.everwing.cloud.service.platform.group.AddGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author DELL shiny
 * @create 2019/12/5
 */
@Data
public class UserVo implements Serializable {

    private String id;

    @NotBlank(message = "用户名不能为空",groups = AddGroup.class)
    private String username;

    @NotBlank(message = "电话号码不能为空",groups = AddGroup.class)
    @Size(min = 8,max = 12,message = "电话号码长度错误",groups = AddGroup.class)
    @Pattern(regexp = "^1[3456789]\\d{9}$",message = "电话号码格式不正确",groups = AddGroup.class)
    private String mobile;

    @NotBlank(message = "密码不能为空",groups = AddGroup.class)
    @Pattern(regexp = "^[a-zA-Z]\\w{5,17}$",message = "密码格式不正确",groups = AddGroup.class)
    private String password;

    public User convertToUser(){
        User user=new User();
        BeanUtil.copyProperties(this,user);
        return user;
    }
}
