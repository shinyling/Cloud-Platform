package com.everwing.cloud.service.platform.biz;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.service.platform.entity.User;
import com.everwing.cloud.service.platform.entity.UserGroupUser;
import com.everwing.cloud.service.platform.param.PagedParam;
import com.everwing.cloud.service.platform.service.IUserGroupUserService;
import com.everwing.cloud.service.platform.service.IUserService;
import com.everwing.cloud.service.platform.validate.ValidList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author DELL shiny
 * @date 2020/3/16
 */
@Slf4j
@Component
public class UserGroupUserBiz {

    @Autowired
    private IUserGroupUserService userGroupUserService;

    @Autowired
    private IUserService userService;

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

    public ResultJson loadUsersByGid(PagedParam<UserGroupUser> pagedParam) {
        UserGroupUser userGroupUser = pagedParam.getT();
        String groupId = userGroupUser.getGroupId();
        QueryWrapper<UserGroupUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserGroupUser::getGroupId, groupId);
        IPage<UserGroupUser> groupUserIPage = userGroupUserService.page(pagedParam.getPage(), queryWrapper);
        List<String> userIds = groupUserIPage.getRecords().stream().map(UserGroupUser::getUserId).collect(Collectors.toList());
        if (CollUtil.isNotEmpty(userIds)) {
            QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
            userQueryWrapper.lambda().in(User::getId, userIds);
            return ResultJson.success(userService.list(userQueryWrapper));
        }
        return ResultJson.success(CollUtil.newArrayList());
    }
}
