package com.everwing.cloud.service.platform.biz;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.service.platform.entity.Element;
import com.everwing.cloud.service.platform.entity.PermissionElement;
import com.everwing.cloud.service.platform.service.IElementService;
import com.everwing.cloud.service.platform.service.IPermissionElementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author DELL shiny
 * @date 2020/3/18
 */
@Slf4j
@Component
public class ElementBiz {

    @Autowired
    private IElementService elementService;

    @Autowired
    private IPermissionElementService permissionElementService;

    public ResultJson getByPermissionId(String permissionId) {
        QueryWrapper<PermissionElement> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(PermissionElement::getPermissionId, permissionId);
        List<PermissionElement> permissionElementList = permissionElementService.list(queryWrapper);
        List<String> elementIds = permissionElementList.stream().map(permissionElement -> permissionElement.getElementId())
                .collect(Collectors.toList());
        if (CollUtil.isNotEmpty(elementIds)) {
            QueryWrapper<Element> elementQueryWrapper = new QueryWrapper<>();
            elementQueryWrapper.lambda().in(Element::getId, elementIds);
            return ResultJson.success(elementService.list(elementQueryWrapper));
        }
        return ResultJson.success(Collections.emptyList());
    }

    public ResultJson deleteById(String id) {
        boolean flag = elementService.removeById(id);
        if (flag) {
            return ResultJson.successWithMsg("删除成功!");
        }
        return ResultJson.fail("删除元素失败!");
    }

    public ResultJson add(Element element) {
        QueryWrapper<Element> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Element::getName, element.getName());
        Element stored = elementService.getOne(queryWrapper);
        if (stored != null) {
            return ResultJson.fail("该元素名称已存在,新增失败!");
        }
        boolean flag = elementService.save(element);
        if (flag) {
            return ResultJson.successWithMsg("新增元素成功!");
        }
        return ResultJson.fail("新增元素失败!");
    }
}
