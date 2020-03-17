package com.everwing.cloud.service.platform.biz;

import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.service.platform.entity.UserGroupUser;
import com.everwing.cloud.service.platform.service.IUserGroupUserService;
import com.everwing.cloud.service.platform.validate.ValidList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author DELL shiny
 * @date 2020/3/16
 */
@Slf4j
@Component
public class UserGroupUserBiz {

    @Autowired
    private IUserGroupUserService userGroupUserService;

    public ResultJson add(UserGroupUser userGroupUser) {
        boolean flag = userGroupUserService.save(userGroupUser);
        if (flag) {
            return ResultJson.successWithMsg("用户组用户关系保存成功!");
        }
        return ResultJson.fail("用户组用户关系保存失败!");
    }

    public ResultJson batchAdd(ValidList<UserGroupUser> userGroupUserList) {
        boolean flag = userGroupUserService.saveBatch(userGroupUserList);
        if (flag) {
            return ResultJson.successWithMsg("用户组用户关系批量保存成功!");
        }
        return ResultJson.fail("用户组用户关系批量保存失败!");
    }

}
