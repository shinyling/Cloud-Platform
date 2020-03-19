package com.everwing.cloud.service.platform.web.fronted;


import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.service.platform.anno.SysLog;
import com.everwing.cloud.service.platform.biz.PermissionOperationBiz;
import com.everwing.cloud.service.platform.entity.PermissionOperation;
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
 * @since 2019-12-05
 */
@Api(value = "权限-操作", tags = "权限-操作")
@RestController
@RequestMapping("/permissionOperation")
public class PermissionOperationController {

    @Autowired
    private PermissionOperationBiz permissionOperationBiz;

    @ApiOperation("新增权限-操作")
    @PostMapping("add")
    @SysLog("新增权限-操作")
    public ResultJson add(@Validated(AddGroup.class) @RequestBody PermissionOperation permissionOperation) {
        return permissionOperationBiz.add(permissionOperation);
    }

    @ApiOperation("删除权限-操作")
    @PostMapping("delete")
    @SysLog("删除权限-操作")
    public ResultJson delete(@Validated(AddGroup.class) @RequestBody PermissionOperation permissionOperation) {
        return permissionOperationBiz.delete(permissionOperation);
    }
}
