package com.everwing.cloud.service.platform.web.fronted;


import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.service.platform.anno.SysLog;
import com.everwing.cloud.service.platform.biz.UserGroupUserBiz;
import com.everwing.cloud.service.platform.entity.UserGroupUser;
import com.everwing.cloud.service.platform.group.AddGroup;
import com.everwing.cloud.service.platform.validate.ValidList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户组用户关系
 *
 * @author shiny
 * @date 2020-03-16
 */
@Api(value = "用户组-用户", tags = "用户组-用户")
@RestController
@RequestMapping("/userGroupUser")
public class UserGroupUserController {

    @Autowired
    private UserGroupUserBiz userGroupUserBiz;

    @ApiOperation("新增用户组-用户")
    @PostMapping("add")
    @SysLog("新增用户组-用户")
    public ResultJson add(@RequestBody @Validated(AddGroup.class) UserGroupUser userGroupUser) {
        return userGroupUserBiz.add(userGroupUser);
    }

    @ApiOperation("批量新增用户组-用户")
    @PostMapping("batchAdd")
    @SysLog("批量新增用户组-用户")
    public ResultJson batchAdd(@Validated @RequestBody ValidList<UserGroupUser> userGroupUserList) {
        return userGroupUserBiz.batchAdd(userGroupUserList);
    }

}
