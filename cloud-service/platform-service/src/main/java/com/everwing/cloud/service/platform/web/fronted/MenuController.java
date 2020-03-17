package com.everwing.cloud.service.platform.web.fronted;


import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.service.platform.anno.SysLog;
import com.everwing.cloud.service.platform.biz.MenuBiz;
import com.everwing.cloud.service.platform.entity.Menu;
import com.everwing.cloud.service.platform.group.AddGroup;
import com.everwing.cloud.service.platform.group.UpdateGroup;
import com.everwing.cloud.service.platform.param.PagedParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author shiny
 * @since 2019-12-05
 */
@Api(value = "菜单", tags = "菜单")
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuBiz menuBiz;

    @ApiOperation("新增菜单")
    @PostMapping("add")
    @SysLog("新增菜单")
    public ResultJson add(@Validated(AddGroup.class) @RequestBody Menu menu) {
        return menuBiz.add(menu);
    }

    @ApiOperation("删除菜单")
    @DeleteMapping("delete/{id}")
    @SysLog("删除菜单")
    public ResultJson delete(@PathVariable String id) {
        Assert.notNull(id, "参数错误!");
        return menuBiz.deleteById(id);
    }

    @ApiOperation("修改菜单")
    @PostMapping("update")
    @SysLog("修改菜单")
    public ResultJson update(@Validated(UpdateGroup.class) @RequestBody Menu menu) {
        return menuBiz.updateById(menu);
    }

    @ApiOperation("菜单列表")
    @PostMapping("loadPage")
    @SysLog("菜单列表")
    public ResultJson loadPage(@RequestBody PagedParam<Menu> pagedParam) {
        Assert.notNull(pagedParam.getPage(), "分页参数错误!");
        Assert.state(pagedParam.getPage().getCurrent() > 0, "分页参数错误!");
        Assert.state(pagedParam.getPage().getSize() > 0, "分页参数错误!");
        return menuBiz.loadPage(pagedParam);
    }
}
