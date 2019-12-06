package com.everwing.cloud.service.platform.vo;

import com.everwing.cloud.service.platform.entity.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author DELL shiny
 * @create 2019/12/5
 */
@Data
public class AccountVo implements Serializable {

    private UserVo userVo;

    private List<Role> roles;

    private List<Menu> menus;

    private List<Permission> permissions;

    private List<Element> elements;

}
