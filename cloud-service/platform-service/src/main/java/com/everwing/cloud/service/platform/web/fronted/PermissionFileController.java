package com.everwing.cloud.service.platform.web.fronted;


import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.service.platform.anno.SysLog;
import com.everwing.cloud.service.platform.biz.PermissionFileBiz;
import com.everwing.cloud.service.platform.entity.PermissionFile;
import com.everwing.cloud.service.platform.group.AddGroup;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 *  权限文件
 * @author shiny
 * @since 2019-12-05
 */
@RestController
@RequestMapping("/permissionFile")
public class PermissionFileController {

    @Autowired
    private PermissionFileBiz permissionFileBiz;

    @ApiOperation("新增权限文件绑定")
    @PostMapping("add")
    @SysLog("新增权限文件绑定")
    public ResultJson add(@Validated(AddGroup.class) @RequestBody PermissionFile permissionFile){
        return permissionFileBiz.add(permissionFile);
    }

    @ApiOperation("删除权限文件绑定关系")
    @PostMapping("delete")
    @SysLog("删除权限文件绑定关系")
    public ResultJson delete(@Validated(AddGroup.class) @RequestBody PermissionFile permissionFile){
        return permissionFileBiz.delete(permissionFile);
    }
}
