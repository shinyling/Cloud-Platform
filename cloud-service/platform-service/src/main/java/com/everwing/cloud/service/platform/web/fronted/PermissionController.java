package com.everwing.cloud.service.platform.web.fronted;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.service.platform.anno.SysLog;
import com.everwing.cloud.service.platform.biz.PermissionBiz;
import com.everwing.cloud.service.platform.entity.Permission;
import com.everwing.cloud.service.platform.group.AddGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 权限
 *
 * @author shiny
 * @since 2019-12-05
 */
@Api(value = "权限", tags = "权限")
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionBiz permissionBiz;

    @ApiOperation("加载权限列表")
    @PostMapping("loadPage")
    @SysLog("加载权限列表")
    public ResultJson loadPage(Page page) {
        Assert.notNull(page, "分页参数错误！");
        Assert.state(page.getCurrent() > 0, "分页参数错误!");
        Assert.state(page.getSize() > 0, "分页参数错误！");
        return permissionBiz.loadPage(page);
    }

    @ApiOperation("添加权限")
    @PostMapping("add")
    @SysLog("添加权限")
    public ResultJson add(@Validated(AddGroup.class) @RequestBody Permission permission) {
        return permissionBiz.add(permission);
    }

    @ApiOperation("根据id删除权限")
    @DeleteMapping("delete/id/{id}")
    @SysLog("根据id删除权限")
    public ResultJson deleteById(@PathVariable String id) {
        Assert.notNull(id, "参数错误!");
        return permissionBiz.delete(id);
    }

}
