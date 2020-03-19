package com.everwing.cloud.service.platform.web.fronted;


import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.service.platform.anno.SysLog;
import com.everwing.cloud.service.platform.biz.RolePermissionBiz;
import com.everwing.cloud.service.platform.entity.RolePermission;
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
 * @author shiny
 * @since 2019-12-05
 */
@Api(value = "角色-权限", tags = "角色-权限")
@RestController
@RequestMapping("/rolePermission")
public class RolePermissionController {

    @Autowired
    private RolePermissionBiz rolePermissionBiz;

    @ApiOperation("新增角色-权限")
    @PostMapping("add")
    @SysLog("新增角色-权限")
    public ResultJson add(@Validated(AddGroup.class) @RequestBody RolePermission rolePermission) {
        return rolePermissionBiz.add(rolePermission);
    }

    @ApiOperation("删除角色-权限")
    @PostMapping("delete")
    @SysLog("删除角色-权限")
    public ResultJson delete(@Validated(AddGroup.class) @RequestBody RolePermission rolePermission) {
        return rolePermissionBiz.delete(rolePermission);
    }
}
