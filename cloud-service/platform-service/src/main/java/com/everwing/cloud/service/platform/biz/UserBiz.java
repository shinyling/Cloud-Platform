package com.everwing.cloud.service.platform.biz;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.common.exception.BusinessException;
import com.everwing.cloud.service.platform.entity.Permission;
import com.everwing.cloud.service.platform.entity.Role;
import com.everwing.cloud.service.platform.entity.User;
import com.everwing.cloud.service.platform.entity.UserGroupUser;
import com.everwing.cloud.service.platform.enums.IsLockEnum;
import com.everwing.cloud.service.platform.param.PagedParam;
import com.everwing.cloud.service.platform.service.IUserGroupUserService;
import com.everwing.cloud.service.platform.service.IUserService;
import com.everwing.cloud.service.platform.vo.AccountVo;
import com.everwing.cloud.service.platform.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author DELL shiny
 * @date 2019/12/5
 */
@Slf4j
@Component
public class UserBiz {

    @Autowired
    private IUserService userService;

    @Autowired
    private RoleBiz roleBiz;

    @Autowired
    private PermissionBiz permissionBiz;

    @Autowired
    private IUserGroupUserService userGroupUserService;

    public AccountVo queryUserInfo(String mobile) throws BusinessException {
        AccountVo accountVo = new AccountVo();
        QueryWrapper<User> userWrapper = new QueryWrapper<>();
        userWrapper.lambda()
                .eq(User::getMobile, mobile)
                .eq(User::getIsDelete, 0)
                .eq(User::getStatus, 1);
        User user = userService.getOne(userWrapper);
        if (user == null) {
            throw new BusinessException("账号不存在!");
        }
        if (user.getIsLock().equals(IsLockEnum.LOCKED)) {
            throw new BusinessException("账号已被锁定!");
        }
        accountVo.setUserVo(user.convertToUserVo());
        List<Role> roles = roleBiz.queryRolesByUserId(user.getId());
        accountVo.setRoles(roles);
        List<Permission> permissions = permissionBiz.queryPermissionByRoles(roles.stream().map(Role::getId).collect(Collectors.toList()));
        accountVo.setPermissions(permissions);
        return accountVo;
    }

    @Transactional(rollbackFor = Exception.class)
    public ResultJson addUser(UserVo userVo) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(User::getUsername, userVo.getUsername()).or()
                .eq(User::getMobile, userVo.getMobile());
        List<User> users = userService.list(queryWrapper);
        if (CollUtil.isNotEmpty(users)) {
            return ResultJson.fail("账号已被注册!");
        }
        userVo.setPassword(new BCryptPasswordEncoder().encode(userVo.getPassword()));
        User user = userVo.convertToUser();
        boolean res = userService.save(user);
        if (res) {
            Boolean grantRole = roleBiz.grantBasicUser(user.getId());
            if (grantRole) {
                return ResultJson.successWithMsg("注册成功!");
            }
            log.debug("角色分配失败，注册失败!");
        }
        return ResultJson.fail("注册失败!");
    }

    /**
     * 逻辑删除不删除角色等其它关联信息
     *
     * @param mobile
     * @return
     */
    public ResultJson deleteUser(String mobile) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(User::getMobile, mobile);
        if (userService.remove(queryWrapper)) {
            return ResultJson.successWithMsg("用户删除成功!");
        }
        return ResultJson.fail("用户删除失败!");
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

    public ResultJson loadPagedUser(PagedParam<UserVo> queryParam) {
        UserVo userVo = queryParam.getT();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "username", "mobile", "is_lock", "create_time");
        queryWrapper.lambda().like(userVo != null && StringUtils.isNotBlank(userVo.getUsername()), User::getUsername, userVo.getUsername())
                .like(StringUtils.isNotBlank(userVo.getMobile()), User::getMobile, userVo.getMobile());
        String[] ascArr = queryParam.getAscArr();
        String[] descArr = queryParam.getDescArr();
        queryWrapper.orderBy(ascArr != null && ascArr.length != 0, true, ascArr);
        queryWrapper.orderBy(descArr != null && descArr.length != 0, false, descArr);
        Page page = queryParam.getPage();
        return ResultJson.success(userService.page(page, queryWrapper));
    }

    public ResultJson updateUser(User user) {
        boolean flag = userService.updateById(user);
        if (flag) {
            return ResultJson.success("更新成功!");
        }
        return ResultJson.fail("更新失败!");
    }
}
