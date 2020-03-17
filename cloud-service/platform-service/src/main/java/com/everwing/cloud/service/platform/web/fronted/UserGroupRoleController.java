package com.everwing.cloud.service.platform.web.fronted;


import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.service.platform.anno.SysLog;
import com.everwing.cloud.service.platform.biz.UserGroupRoleBiz;
import com.everwing.cloud.service.platform.entity.UserGroupRole;
import com.everwing.cloud.service.platform.group.AddGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author shiny
 * @date 2019-12-05
 */
@Api(value = "用户组角色关联", tags = "用户组角色关联")
@RestController
@RequestMapping("/userGroupRole")
public class UserGroupRoleController {

    @Autowired
    private UserGroupRoleBiz userGroupRoleBiz;

    @ApiOperation("增加用户组角色关联")
    @PostMapping("add")
    @SysLog("增加用户组角色关联")
    public ResultJson add(@Validated(AddGroup.class) @RequestBody UserGroupRole userGroupRole) {
        return userGroupRoleBiz.add(userGroupRole);
    }

    @ApiOperation("删除用户组角色关联")
    @PostMapping("delete")
    @SysLog("删除用户组角色关联")
    public ResultJson delete(@Validated(AddGroup.class) @RequestBody UserGroupRole userGroupRole) {
        return userGroupRoleBiz.delete(userGroupRole);
    }

    @ApiOperation("查找组的角色")
    @GetMapping("findRolesBygroup/{groupId}")
    @SysLog("查找组的角色")
    public ResultJson findRolesByGroup(@PathVariable String groupId) {
        Assert.notNull(groupId, "参数错误!");
        return userGroupRoleBiz.loadRoleByGroupId(groupId);
    }
}
