package com.everwing.cloud.service.platform.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.service.platform.entity.UserGroupPermission;
import com.everwing.cloud.service.platform.service.IUserGroupPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author DELL shiny
 * @date 2020/3/19
 */
@Slf4j
@Component
public class UserGroupPermissionBiz {

    @Autowired
    private IUserGroupPermissionService userGroupPermissionService;

    public ResultJson add(UserGroupPermission userGroupPermission) {
        QueryWrapper<UserGroupPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserGroupPermission::getGroupId, userGroupPermission.getGroupId())
                .eq(UserGroupPermission::getPermissionId, userGroupPermission.getPermissionId());
        UserGroupPermission stored = userGroupPermissionService.getOne(queryWrapper);
        if (stored != null) {
            return ResultJson.fail("用户组-权限已存在!");
        }
        boolean flag = userGroupPermissionService.save(userGroupPermission);
        if (flag) {
            return ResultJson.successWithMsg("用户组-权限绑定成功!");
        }
        return ResultJson.fail("用户组-权限绑定失败!");
    }

    public ResultJson delete(UserGroupPermission userGroupPermission) {
        QueryWrapper<UserGroupPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserGroupPermission::getGroupId, userGroupPermission.getGroupId())
                .eq(UserGroupPermission::getPermissionId, userGroupPermission.getPermissionId());
        boolean flag = userGroupPermissionService.remove(queryWrapper);
        if (flag) {
            return ResultJson.successWithMsg("解除用户组-权限成功!");
        }
        return ResultJson.fail("解除用户组-权限失败!");
    }
}
