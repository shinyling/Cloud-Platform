package com.everwing.cloud.service.platform.web.fronted;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.service.platform.anno.SysLog;
import com.everwing.cloud.service.platform.biz.RoleBiz;
import com.everwing.cloud.service.platform.entity.Role;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

/**
 * @author shiny
 * @date 2019-12-05
 */
@Api(value = "角色", tags = "角色")
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleBiz roleBiz;

    @ApiOperation("新增角色")
    @PostMapping("add")
    @SysLog("新增角色")
    public ResultJson add(@RequestBody Role role) {
        return roleBiz.add(role);
    }

    @ApiOperation("删除角色")
    @GetMapping("delete/{id}")
    @SysLog("删除角色")
    public ResultJson delete(@PathVariable String id) {
        return roleBiz.removeById(id);
    }

    @ApiOperation("查看角色列表")
    @PostMapping("loadPage")
    @SysLog("查看角色列表")
    public ResultJson loadPage(@RequestBody Page page) {
        Assert.notNull(page, "请传人分页参数!");
        Assert.state(page.getCurrent() > 0, "分页参数错误!");
        Assert.state(page.getSize() > 0, "分页参数错误!");
        return roleBiz.loadPage(page);
    }

    @ApiOperation("查找组的角色")
    @GetMapping("findRolesByGroupId/{groupId}")
    @SysLog("查找组的角色")
    public ResultJson findRolesByGroupId(@PathVariable String groupId) {
        Assert.notNull(groupId, "参数错误!");
        return roleBiz.loadRolesByGroupId(groupId);
    }
}
