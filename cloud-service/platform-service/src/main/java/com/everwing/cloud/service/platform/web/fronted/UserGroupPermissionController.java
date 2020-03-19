package com.everwing.cloud.service.platform.web.fronted;


import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.service.platform.anno.SysLog;
import com.everwing.cloud.service.platform.biz.UserGroupPermissionBiz;
import com.everwing.cloud.service.platform.entity.UserGroupPermission;
import com.everwing.cloud.service.platform.group.AddGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author shiny
 * @since 2020-03-16
 */
@Api(value = "用户组-权限", tags = "用户组-权限")
@RestController
@RequestMapping("/userGroupPermission")
public class UserGroupPermissionController {

    @Autowired
    private UserGroupPermissionBiz userGroupPermissionBiz;

    @ApiOperation("增加用户组-权限")
    @PostMapping("add")
    @SysLog("增加用户组-权限")
    public ResultJson add(@Validated(AddGroup.class) @RequestBody UserGroupPermission userGroupPermission) {
        return userGroupPermissionBiz.add(userGroupPermission);
    }

    @ApiOperation("删除用户组-权限")
    @PostMapping("delete")
    @SysLog("删除用户组-权限")
    public ResultJson delete(@Validated(AddGroup.class) @RequestBody UserGroupPermission userGroupPermission) {
        return userGroupPermissionBiz.delete(userGroupPermission);
    }
}
