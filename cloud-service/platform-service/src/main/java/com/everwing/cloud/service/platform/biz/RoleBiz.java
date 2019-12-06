package com.everwing.cloud.service.platform.biz;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.everwing.cloud.service.platform.entity.Role;
import com.everwing.cloud.service.platform.entity.UserRole;
import com.everwing.cloud.service.platform.service.IRoleService;
import com.everwing.cloud.service.platform.service.IUserRoleService;
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
public class RoleBiz {

    @Autowired
    private IUserRoleService userRoleService;

    @Autowired
    private IRoleService roleService;

    public List<Role> queryRolesByUserId(String userId){
        QueryWrapper<UserRole> userRoleQueryWrapper=new QueryWrapper<>();
        userRoleQueryWrapper.eq("user_id",userId);
        List<UserRole> userRoles=userRoleService.list(userRoleQueryWrapper);
        if(CollectionUtil.isEmpty(userRoles)){
            log.debug("用户:[{}]未绑定角色",userId);
            return null;
        }else {
            List<String> roleIds=userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList());
            QueryWrapper<Role> roleQueryWrapper=new QueryWrapper<>();
            roleQueryWrapper.in("id",roleIds);
            List<Role> roles=roleService.list(roleQueryWrapper);
            return roles;
        }
    }
}
