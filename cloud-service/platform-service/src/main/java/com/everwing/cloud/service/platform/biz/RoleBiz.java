package com.everwing.cloud.service.platform.biz;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.service.platform.entity.Role;
import com.everwing.cloud.service.platform.entity.UserRole;
import com.everwing.cloud.service.platform.service.IRoleService;
import com.everwing.cloud.service.platform.service.IUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author DELL shiny
 * @create 2019/12/5
 */
@Slf4j
@Component
public class RoleBiz {

    @Autowired
    private IUserRoleService userRoleService;

    @Autowired
    private IRoleService roleService;

    public List<Role> queryRolesByUserId(String userId) {
        QueryWrapper<UserRole> userRoleQueryWrapper = new QueryWrapper<>();
        userRoleQueryWrapper.lambda().eq(UserRole::getUserId, userId);
        List<UserRole> userRoles = userRoleService.list(userRoleQueryWrapper);
        if (CollUtil.isEmpty(userRoles)) {
            log.debug("用户:[{}]未绑定角色", userId);
            return Collections.emptyList();
        } else {
            List<String> roleIds = userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList());
            QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
            roleQueryWrapper.lambda().in(Role::getId, roleIds);
            return roleService.list(roleQueryWrapper);
        }
    }

    public Boolean grantBasicUser(String id) {
        QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
        roleQueryWrapper.lambda()
                .eq(Role::getName, "USER");
        Role role = roleService.getOne(roleQueryWrapper);
        if (role == null) {
            log.error("基础角色未创建,用户创建失败!");
            return false;
        }
        UserRole userRole = new UserRole();
        userRole.setRoleId(role.getId());
        userRole.setUserId(id);
        return userRoleService.save(userRole);
    }

    public ResultJson add(Role role) {
        boolean flag = roleService.save(role);
        if (flag) {
            return ResultJson.successWithMsg("角色保存成功.");
        }
        return ResultJson.fail("角色保存失败!");
    }

    public ResultJson removeById(String id) {
        boolean flag = roleService.removeById(id);
        if (flag) {
            return ResultJson.successWithMsg("删除成功。");
        }
        return ResultJson.fail("删除失败!");
    }

    public ResultJson loadPage(Page page) {
        return ResultJson.success(roleService.page(page));
    }
}
