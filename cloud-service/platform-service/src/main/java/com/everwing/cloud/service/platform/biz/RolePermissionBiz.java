package com.everwing.cloud.service.platform.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.service.platform.entity.RolePermission;
import com.everwing.cloud.service.platform.service.IRolePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author DELL shiny
 * @date 2020/3/19
 */
@Slf4j
@Component
public class RolePermissionBiz {

    @Autowired
    private IRolePermissionService rolePermissionService;

    public ResultJson add(RolePermission rolePermission) {
        QueryWrapper<RolePermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(RolePermission::getRoleId, rolePermission.getRoleId())
                .eq(RolePermission::getPermissionId, rolePermission.getPermissionId());
        RolePermission stored = rolePermissionService.getOne(queryWrapper);
        if (stored != null) {
            return ResultJson.fail("角色-权限已存在!");
        }
        boolean flag = rolePermissionService.save(rolePermission);
        if (flag) {
            return ResultJson.successWithMsg("角色-权限绑定成功!");
        }
        return ResultJson.fail("角色-权限绑定失败!");
    }

    public ResultJson delete(RolePermission rolePermission) {
        QueryWrapper<RolePermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(RolePermission::getRoleId, rolePermission.getRoleId())
                .eq(RolePermission::getPermissionId, rolePermission.getPermissionId());
        boolean flag = rolePermissionService.remove(queryWrapper);
        if (flag) {
            return ResultJson.successWithMsg("解除角色-权限成功!");
        }
        return ResultJson.fail("解除角色-权限失败!");
    }
}
