package com.everwing.cloud.service.platform.web.fronted;


import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.service.platform.anno.SysLog;
import com.everwing.cloud.service.platform.biz.PermissionFileBiz;
import com.everwing.cloud.service.platform.entity.PermissionFile;
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
 * 权限文件
 *
 * @author shiny
 * @since 2019-12-05
 */
@Api(value = "权限-文件", tags = "权限-文件")
@RestController
@RequestMapping("/permissionFile")
public class PermissionFileController {

    @Autowired
    private PermissionFileBiz permissionFileBiz;

    @ApiOperation("新增权限-文件")
    @PostMapping("add")
    @SysLog("新增权限-文件")
    public ResultJson add(@Validated(AddGroup.class) @RequestBody PermissionFile permissionFile) {
        return permissionFileBiz.add(permissionFile);
    }

    @ApiOperation("删除权限-文件")
    @PostMapping("delete")
    @SysLog("删除权限-文件")
    public ResultJson delete(@Validated(AddGroup.class) @RequestBody PermissionFile permissionFile) {
        return permissionFileBiz.delete(permissionFile);
    }
}
