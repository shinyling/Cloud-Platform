package com.everwing.cloud.service.platform.web.fronted;


import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.service.platform.anno.SysLog;
import com.everwing.cloud.service.platform.biz.UserGroupBiz;
import com.everwing.cloud.service.platform.entity.UserGroup;
import com.everwing.cloud.service.platform.param.PagedParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

/**
 * @author shiny
 * @since 2019-12-05
 */
@Api(value = "用户组", tags = "用户组")
@RestController
@RequestMapping("/userGroup")
public class UserGroupController {

    @Autowired
    private UserGroupBiz userGroupBiz;

    @ApiOperation("添加用户组")
    @PostMapping("add")
    @SysLog("添加用户组")
    public ResultJson add(@RequestBody UserGroup userGroup) {
        return userGroupBiz.add(userGroup);
    }

    @ApiOperation("删除用户组")
    @GetMapping("delete/{id}")
    @SysLog("删除用户组")
    public ResultJson delete(@PathVariable String id) {
        Assert.state(id != null, "参数错误!");
        return userGroupBiz.removeById(id);
    }

    @ApiOperation("加载用户组列表")
    @PostMapping("loadPage")
    @SysLog("加载用户组列表")
    public ResultJson loadPage(@RequestBody PagedParam<UserGroup> pagedParam) {
        Assert.state(pagedParam.getPage() != null, "分页参数错误!");
        return userGroupBiz.loadPage(pagedParam);
    }

}
