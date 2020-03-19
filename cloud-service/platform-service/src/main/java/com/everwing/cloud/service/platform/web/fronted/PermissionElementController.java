package com.everwing.cloud.service.platform.web.fronted;


import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.service.platform.anno.SysLog;
import com.everwing.cloud.service.platform.biz.PermissionElementBiz;
import com.everwing.cloud.service.platform.entity.PermissionElement;
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
 * 权限元素
 *
 * @author shiny
 * @since 2019-12-05
 */
@Api(value = "权限-元素", tags = "权限-元素")
@RestController
@RequestMapping("/permissionElement")
public class PermissionElementController {

    @Autowired
    private PermissionElementBiz permissionElementBiz;

    @ApiOperation("新增权限-元素")
    @PostMapping("add")
    @SysLog("新增权限-元素")
    public ResultJson add(@Validated(AddGroup.class) @RequestBody PermissionElement permissionElement) {
        return permissionElementBiz.add(permissionElement);
    }

    @ApiOperation("删除权限-元素")
    @PostMapping("delete")
    @SysLog("删除权限-元素")
    public ResultJson deleteById(@Validated(AddGroup.class) @RequestBody PermissionElement permissionElement) {
        return permissionElementBiz.delete(permissionElement);
    }

}
