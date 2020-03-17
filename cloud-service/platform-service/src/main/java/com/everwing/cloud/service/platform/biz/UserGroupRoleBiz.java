package com.everwing.cloud.service.platform.biz;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.service.platform.entity.Role;
import com.everwing.cloud.service.platform.entity.UserGroupRole;
import com.everwing.cloud.service.platform.service.IRoleService;
import com.everwing.cloud.service.platform.service.IUserGroupRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author DELL shiny
 * @date 2020/3/17
 */
@Slf4j
@Component
public class UserGroupRoleBiz {

    @Autowired
    private IUserGroupRoleService userGroupRoleService;

    @Autowired
    private IRoleService roleService;

    public ResultJson add(UserGroupRole userGroupRole) {
        QueryWrapper<UserGroupRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserGroupRole::getGroupId, userGroupRole.getGroupId())
                .eq(UserGroupRole::getRoleId, userGroupRole.getRoleId());
        UserGroupRole stored = userGroupRoleService.getOne(queryWrapper);
        if (stored != null) {
            return ResultJson.fail("该用户组角色绑定关系已存在!");
        }
        boolean flag = userGroupRoleService.save(userGroupRole);
        if (flag) {
            return ResultJson.success("新增用户组角色成功!");
        }
        return ResultJson.fail("新增用户组角色失败!");
    }

    public ResultJson delete(UserGroupRole userGroupRole) {
        QueryWrapper<UserGroupRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserGroupRole::getGroupId, userGroupRole.getGroupId())
                .eq(UserGroupRole::getRoleId, userGroupRole.getRoleId());
        boolean flag = userGroupRoleService.remove(queryWrapper);
        if (flag) {
            return ResultJson.successWithMsg("删除用户组角色绑定关系成功!");
        }
        return ResultJson.fail("删除用户组角色绑定关系失败!");
    }

    public ResultJson loadRoleByGroupId(String groupId) {
        QueryWrapper<UserGroupRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserGroupRole::getGroupId, groupId);
        List<UserGroupRole> userGroupRoleList = userGroupRoleService.list(queryWrapper);
        List<String> roleIds = userGroupRoleList.stream()
                .map(userGroupRole -> userGroupRole.getRoleId()).collect(Collectors.toList());
        if (CollUtil.isNotEmpty(roleIds)) {
            QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
            roleQueryWrapper.lambda().in(Role::getId, roleIds);
            return ResultJson.success(roleService.list(roleQueryWrapper));
        }
        return ResultJson.success(Collections.emptyList());
    }
}
