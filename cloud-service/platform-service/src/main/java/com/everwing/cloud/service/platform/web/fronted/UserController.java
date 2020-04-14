package com.everwing.cloud.service.platform.web.fronted;

import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.common.exception.BusinessException;
import com.everwing.cloud.service.platform.anno.SysLog;
import com.everwing.cloud.service.platform.biz.UserBiz;
import com.everwing.cloud.service.platform.entity.User;
import com.everwing.cloud.service.platform.entity.UserGroupUser;
import com.everwing.cloud.service.platform.group.AddGroup;
import com.everwing.cloud.service.platform.group.LoadGroup;
import com.everwing.cloud.service.platform.param.PagedParam;
import com.everwing.cloud.service.platform.vo.AccountVo;
import com.everwing.cloud.service.platform.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理
 *
 * @author shiny
 * @date 2019-12-05
 */
@Api(value = "用户", tags = "用户")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserBiz userBiz;

    @ApiOperation(value = "通过账号加载用户信息")
    @GetMapping("load/{mobile:1[3456789]\\d{9}}")
    @SysLog("通过账号加载用户信息")
    public ResultJson loadByMobile(@PathVariable String mobile) throws BusinessException {
        AccountVo accountVo = userBiz.queryUserInfo(mobile);
        return ResultJson.success(accountVo);
    }

    @ApiOperation(value = "用户注册")
    @PostMapping("register")
    @SysLog("用户注册")
    public ResultJson register(@RequestBody @Validated(AddGroup.class) UserVo userVo) {
        return userBiz.addUser(userVo);
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("delete/{mobile:1[3456789]\\d{9}}")
    @SysLog("删除用户")
    public ResultJson delete(@PathVariable String mobile) {
        return userBiz.deleteUser(mobile);
    }

    @ApiOperation("根据用户组查询用户列表")
    @PostMapping("loadUsersByGid")
    @SysLog("根据用户组查询用户列表")
    public ResultJson loadUsersByGid(@Validated(LoadGroup.class) @RequestBody PagedParam<UserGroupUser> queryParam) {
        Assert.notNull(queryParam.getPage(), "分页参数未传入!");
        Assert.state(queryParam.getPage().getCurrent() > 0, "分页参数错误!");
        Assert.state(queryParam.getPage().getSize() > 0, "分页参数错误!");
        Assert.notNull(queryParam.getT(), "参数传入错误!");
        Assert.state(queryParam.getT().getGroupId() != null, "参数传入错误");
        return userBiz.loadUsersByGid(queryParam);
    }

    @ApiOperation("分页加载所有用户")
    @PostMapping("loadPageData")
    @SysLog("分页加载所有用户")
    public ResultJson loadPageData(@RequestBody PagedParam<UserVo> queryParam) {
        Assert.notNull(queryParam.getPage(), "分页参数未传入!");
        Assert.state(queryParam.getPage().getCurrent() > 0, "分页参数错误!");
        Assert.state(queryParam.getPage().getSize() > 0, "分页参数错误!");
        return userBiz.loadPagedUser(queryParam);
    }

    @ApiOperation("更新用户信息")
    @PostMapping("update")
    @SysLog("更新用户信息")
    public ResultJson updateUser(@RequestBody User user) {
        Assert.notNull(user.getId(), "参数见传入错误!");
        return userBiz.updateUser(user);
    }
}
