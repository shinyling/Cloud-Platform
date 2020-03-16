package com.everwing.cloud.service.platform.biz;

import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.service.platform.entity.UserGroup;
import com.everwing.cloud.service.platform.param.PagedParam;
import com.everwing.cloud.service.platform.service.IUserGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author DELL shiny
 * @date 2020/3/16
 */
@Slf4j
@Component
public class UserGroupBiz {

    @Autowired
    private IUserGroupService userGroupService;

    public ResultJson add(UserGroup userGroup) {
        boolean flag = userGroupService.save(userGroup);
        if (flag) {
            return ResultJson.successWithMsg("新增用户组成功!");
        }
        return ResultJson.fail("新增用户组失败!");
    }

    public ResultJson removeById(String id) {
        boolean flag = userGroupService.removeById(id);
        if (flag) {
            return ResultJson.successWithMsg("删除用户组成功!");
        }
        return ResultJson.fail("删除用户组失败!");
    }

    public ResultJson loadPage(PagedParam<UserGroup> pagedParam) {
        return ResultJson.success(userGroupService.page(pagedParam.getPage()));
    }
}
