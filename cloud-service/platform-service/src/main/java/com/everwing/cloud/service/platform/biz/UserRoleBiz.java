package com.everwing.cloud.service.platform.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.service.platform.entity.UserRole;
import com.everwing.cloud.service.platform.service.IUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author DELL shiny
 * @date 2020/3/17
 */
@Slf4j
@Component
public class UserRoleBiz {

    @Autowired
    private IUserRoleService userRoleService;

    public ResultJson add(UserRole userRole) {
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserRole::getUserId, userRole.getUserId())
                .eq(UserRole::getRoleId, userRole.getRoleId());
        UserRole stored = userRoleService.getOne(queryWrapper);
        if (stored != null) {
            return ResultJson.fail("此用户角色关系已存在");
        }
        boolean flag = userRoleService.save(userRole);
        if (flag) {
            return ResultJson.successWithMsg("保存用户角色关系成功!");
        }
        return ResultJson.fail("保存用户角色关系失败!");
    }

    public ResultJson delete(UserRole userRole) {
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserRole::getUserId, userRole.getUserId())
                .eq(UserRole::getRoleId, userRole.getRoleId());
        boolean flag = userRoleService.remove(queryWrapper);
        if (flag) {
            return ResultJson.successWithMsg("删除用户角色关系成功!");
        }
        return ResultJson.fail("删除用户角色关系失败!");
    }

}
