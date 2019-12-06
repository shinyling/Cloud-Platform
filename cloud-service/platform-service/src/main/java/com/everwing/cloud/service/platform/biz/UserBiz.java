package com.everwing.cloud.service.platform.biz;

import com.everwing.cloud.service.platform.entity.Permission;
import com.everwing.cloud.service.platform.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.everwing.cloud.common.exception.BusinessException;
import com.everwing.cloud.service.platform.entity.User;
import com.everwing.cloud.service.platform.service.IUserService;
import com.everwing.cloud.service.platform.vo.AccountVo;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author DELL shiny
 * @create 2019/12/5
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

    public AccountVo queryUserInfo(String mobile) throws BusinessException {
        AccountVo accountVo=new AccountVo();
        QueryWrapper<User> userWrapper=new QueryWrapper<>();
        userWrapper.eq("mobile",mobile);
        userWrapper.eq("is_delete",0);
        userWrapper.eq("status",1);
        User user=userService.getOne(userWrapper);
        if(user==null){
            throw new BusinessException("账号不存在!");
        }
        if(user.getIsLock()){
            throw new BusinessException("账号已被锁定!");
        }
        accountVo.setUserVo(user.convertToUserVo());
        List<Role> roles=roleBiz.queryRolesByUserId(user.getId());
        accountVo.setRoles(roles);
        List<Permission> permissions=permissionBiz.queryPermissionByRoles(roles.stream().map(Role::getId).collect(Collectors.toList()));
        accountVo.setPermissions(permissions);
        return accountVo;
    }
}
