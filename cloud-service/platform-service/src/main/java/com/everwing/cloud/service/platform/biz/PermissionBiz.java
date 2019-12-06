package com.everwing.cloud.service.platform.biz;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.everwing.cloud.service.platform.entity.Permission;
import com.everwing.cloud.service.platform.entity.RolePermission;
import com.everwing.cloud.service.platform.service.IPermissionService;
import com.everwing.cloud.service.platform.service.IRolePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author DELL shiny
 * @create 2019/12/5
 */
@Slf4j
@Component
public class PermissionBiz {

    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private IRolePermissionService rolePermissionService;

    public List<Permission> queryPermissionByRoles(List<String> roleIds){
        QueryWrapper<RolePermission> rolePermissionQueryWrapper=new QueryWrapper<>();
        rolePermissionQueryWrapper.in("role_id",roleIds);
        List<RolePermission> rolePermissions=rolePermissionService.list(rolePermissionQueryWrapper);
        if(CollectionUtil.isEmpty(rolePermissions)){
            log.debug("未查询到角色:[{}]对应的权限",roleIds);
            return null;
        }
        List<String> permissionIds=rolePermissions.stream().map(RolePermission::getPermissionId).collect(Collectors.toList());
        if(CollectionUtil.isEmpty(permissionIds)){
            log.debug("无权限id，未查询到权限，角色:[{}]",roleIds);
            return null;
        }
        QueryWrapper<Permission> permissionQueryWrapper=new QueryWrapper<>();
        permissionQueryWrapper.in("id",permissionIds);
        return permissionService.list(permissionQueryWrapper);
    }
}
