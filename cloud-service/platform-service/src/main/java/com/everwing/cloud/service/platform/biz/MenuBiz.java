package com.everwing.cloud.service.platform.biz;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.lang.tree.parser.NodeParser;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.service.platform.entity.Menu;
import com.everwing.cloud.service.platform.enums.MenuParentEnum;
import com.everwing.cloud.service.platform.param.PagedParam;
import com.everwing.cloud.service.platform.service.IMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        String[] ascArr = pagedParam.getAscArr();
        String[] descArr = pagedParam.getDescArr();
        queryWrapper.orderBy(ascArr != null && ascArr.length != 0, true, ascArr);
        queryWrapper.orderBy(descArr != null && descArr.length != 0, false, descArr);
        Page page = pagedParam.getPage();
        return ResultJson.success(menuService.page(page, queryWrapper));
    }

    public ResultJson loadParent() {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "name");
        queryWrapper.lambda().eq(Menu::getParent, MenuParentEnum.PARENT);
        return ResultJson.success(menuService.list(queryWrapper));
    }

    public ResultJson menuTree() {
        List<Menu> menuList = menuService.list();
        TreeNodeConfig treeNodeConfig = TreeNodeConfig.DEFAULT_CONFIG;
        NodeParser<Menu, String> nodeParser = new NodeParser<Menu, String>() {
            @Override
            public void parse(Menu menu, Tree<String> tree) {
                tree.setId(menu.getId());
                tree.setParentId(menu.getPid());
                tree.setName(menu.getName());
                tree.setWeight(menu.getLevel());
                tree.putExtra("url", menu.getUrl());
                tree.putExtra("icon", menu.getIcon());
            }
        };
        List<Tree<String>> treeList = TreeUtil.build(menuList, "0", treeNodeConfig, nodeParser);
        return ResultJson.success(treeList);
    }

}
