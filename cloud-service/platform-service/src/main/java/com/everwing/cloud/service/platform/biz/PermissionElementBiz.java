package com.everwing.cloud.service.platform.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.service.platform.entity.PermissionElement;
import com.everwing.cloud.service.platform.service.IPermissionElementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PermissionElementBiz {

    @Autowired
    private IPermissionElementService permissionElementService;

    public ResultJson add(PermissionElement permissionElement){
        QueryWrapper<PermissionElement> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(PermissionElement::getPermissionId,permissionElement.getPermissionId())
                .eq(PermissionElement::getElementId,permissionElement.getElementId());
        PermissionElement stored=permissionElementService.getOne(queryWrapper);
        if(stored!=null){
            return ResultJson.fail("此权限元素已绑定!");
        }
        boolean flag=permissionElementService.save(permissionElement);
        if(flag){
            return ResultJson.successWithMsg("权限元素绑定成功!");
        }
        return ResultJson.fail("权限元素绑定失败!");
    }

    public ResultJson delete(PermissionElement permissionElement){
        QueryWrapper<PermissionElement> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(PermissionElement::getPermissionId,permissionElement.getPermissionId())
                .eq(PermissionElement::getElementId,permissionElement.getElementId());
        boolean flag=permissionElementService.remove(queryWrapper);
        if(flag){
            return ResultJson.successWithMsg("解除权限元素绑定关系成功!");
        }
        return ResultJson.fail("解除权限元素绑定关系失败!");
    }

}
