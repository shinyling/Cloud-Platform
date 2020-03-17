package com.everwing.cloud.service.platform.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.service.platform.entity.UserGroupRole;
import com.everwing.cloud.service.platform.service.IRoleService;
import com.everwing.cloud.service.platform.service.IUserGroupRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

}
