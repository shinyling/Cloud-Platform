package com.everwing.cloud.service.platform.web.fronted;


import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.service.platform.anno.SysLog;
import com.everwing.cloud.service.platform.biz.ElementBiz;
import com.everwing.cloud.service.platform.entity.Element;
import com.everwing.cloud.service.platform.group.AddGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 元素
 *
 * @author shiny
 * @since 2019-12-05
 */
@Api(value = "元素", tags = "元素")
@RestController
@RequestMapping("/element")
public class ElementController {

    @Autowired
    private ElementBiz elementBiz;

    @ApiOperation("添加")
    @PostMapping("add")
    @SysLog("添加")
    public ResultJson add(@Validated(AddGroup.class) @RequestBody Element element) {
        return elementBiz.add(element);
    }

    @ApiOperation("根据id删除")
    @DeleteMapping("delete/{id}")
    @SysLog("根据id删除")
    public ResultJson deleteById(@PathVariable String id) {
        Assert.notNull(id, "参数错误!");
        return elementBiz.deleteById(id);
    }

    @ApiOperation("根据权限查询元素")
    @GetMapping("permission/{permissionId}")
    @SysLog("根据权限查询元素")
    public ResultJson getByPermissionId(@PathVariable String permissionId) {
        Assert.notNull(permissionId, "参数错误!");
        return elementBiz.getByPermissionId(permissionId);
    }
}
