package com.everwing.cloud.service.platform.web.fronted;


import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.service.platform.anno.SysLog;
import com.everwing.cloud.service.platform.biz.UserRoleBiz;
import com.everwing.cloud.service.platform.entity.UserRole;
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
 * 用户角色
 *
 * @author shiny
 * @date 2019-12-05
 */
@Api(value = "用户-角色", tags = "用户-角色")
@RestController
@RequestMapping("/userRole")
public class UserRoleController {

    @Autowired
    private UserRoleBiz userRoleBiz;

    @ApiOperation("新增用户-角色")
    @PostMapping("add")
    @SysLog("新增用户-角色")
    public ResultJson add(@Validated(AddGroup.class) @RequestBody UserRole userRole) {
        return userRoleBiz.add(userRole);
    }

    @ApiOperation("删除用户-角色")
    @PostMapping("delete")
    @SysLog("删除用户-角色")
    public ResultJson delete(@Validated(AddGroup.class) @RequestBody UserRole userRole) {
        return userRoleBiz.delete(userRole);
    }
}
