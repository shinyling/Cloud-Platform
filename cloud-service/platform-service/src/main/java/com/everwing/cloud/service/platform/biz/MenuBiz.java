package com.everwing.cloud.service.platform.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.service.platform.entity.Menu;
import com.everwing.cloud.service.platform.param.PagedParam;
import com.everwing.cloud.service.platform.service.IMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author DELL shiny
 * @date 2020/3/17
 */
@Slf4j
@Component
public class MenuBiz {

    @Autowired
    private IMenuService menuService;


    public ResultJson add(Menu menu) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Menu::getName, menu.getName());
        Menu stored = menuService.getOne(queryWrapper);
        if (stored != null) {
            return ResultJson.fail("菜单名称已存在!");
        }
        boolean flag = menuService.save(menu);
        if (flag) {
            return ResultJson.successWithMsg("添加菜单成功!");
        }
        return ResultJson.fail("添加菜单失败!");
    }

    public ResultJson deleteById(String id) {
        boolean flag = menuService.removeById(id);
        if (flag) {
            return ResultJson.successWithMsg("删除菜单成功!");
        }
        return ResultJson.fail("删除菜单失败!");
    }

    public ResultJson updateById(Menu menu) {
        boolean flag = menuService.updateById(menu);
        if (flag) {
            return ResultJson.successWithMsg("更新菜单成功!");
        }
        return ResultJson.fail("更新菜单失败!");
    }

    public ResultJson loadPage(PagedParam<Menu> pagedParam) {
        Page page = pagedParam.getPage();
        return ResultJson.success(menuService.page(page));
    }
}
