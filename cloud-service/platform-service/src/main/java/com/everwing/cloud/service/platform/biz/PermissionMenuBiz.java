package com.everwing.cloud.service.platform.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.service.platform.entity.PermissionMenu;
import com.everwing.cloud.service.platform.service.IPermissionMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author DELL shiny
 * @date 2020/3/19
 */
@Slf4j
@Component
public class PermissionMenuBiz {

    @Autowired
    private IPermissionMenuService permissionMenuService;

    public ResultJson add(PermissionMenu permissionMenu) {
        QueryWrapper<PermissionMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(PermissionMenu::getPermissionId, permissionMenu.getPermissionId())
                .eq(PermissionMenu::getMenuId, permissionMenu.getMenuId());
        PermissionMenu stored = permissionMenuService.getOne(queryWrapper);
        if (stored != null) {
            return ResultJson.fail("权限-菜单已存在！");
        }
        boolean flag = permissionMenuService.save(permissionMenu);
        if (flag) {
            return ResultJson.successWithMsg("权限-菜单添加成功!");
        }
        return ResultJson.fail("权限-菜单添加失败!");
    }

    public ResultJson delete(PermissionMenu permissionMenu) {
        QueryWrapper<PermissionMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(PermissionMenu::getPermissionId, permissionMenu.getPermissionId())
                .eq(PermissionMenu::getMenuId, permissionMenu.getMenuId());
        boolean flag = permissionMenuService.remove(queryWrapper);
        if (flag) {
            return ResultJson.successWithMsg("权限-菜单删除成功!");
        }
        return ResultJson.fail("权限-菜单删除失败!");
    }
}
