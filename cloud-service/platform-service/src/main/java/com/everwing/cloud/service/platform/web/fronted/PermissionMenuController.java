package com.everwing.cloud.service.platform.web.fronted;


import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.service.platform.anno.SysLog;
import com.everwing.cloud.service.platform.biz.PermissionMenuBiz;
import com.everwing.cloud.service.platform.entity.PermissionMenu;
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
 * 权限-菜单
 *
 * @author shiny
 * @since 2019-12-05
 */
@Api(value = "权限-菜单", tags = "权限-菜单")
@RestController
@RequestMapping("/permissionMenu")
public class PermissionMenuController {

    @Autowired
    private PermissionMenuBiz permissionMenuBiz;

    @ApiOperation("新增权限-菜单")
    @PostMapping("add")
    @SysLog("新增权限-菜单")
    public ResultJson add(@Validated(AddGroup.class) @RequestBody PermissionMenu permissionMenu) {
        return permissionMenuBiz.add(permissionMenu);
    }

    @ApiOperation("删除权限-菜单")
    @PostMapping("delete")
    @SysLog("删除权限-菜单")
    public ResultJson delete(@Validated(AddGroup.class) @RequestBody PermissionMenu permissionMenu) {
        return permissionMenuBiz.delete(permissionMenu);
    }

}
