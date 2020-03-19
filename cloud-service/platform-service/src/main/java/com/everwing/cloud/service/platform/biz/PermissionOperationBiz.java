package com.everwing.cloud.service.platform.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.service.platform.entity.PermissionOperation;
import com.everwing.cloud.service.platform.service.IPermissionOperationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author DELL shiny
 * @date 2020/3/19
 */
@Slf4j
@Component
public class PermissionOperationBiz {

    @Autowired
    private IPermissionOperationService permissionOperationService;

    public ResultJson add(PermissionOperation permissionOperation) {
        QueryWrapper<PermissionOperation> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(PermissionOperation::getPermissionId, permissionOperation.getPermissionId())
                .eq(PermissionOperation::getOperationId, permissionOperation.getOperationId());
        PermissionOperation stored = permissionOperationService.getOne(queryWrapper);
        if (stored != null) {
            return ResultJson.fail("权限-操作已存在！");
        }
        boolean flag = permissionOperationService.save(permissionOperation);
        if (flag) {
            return ResultJson.successWithMsg("权限-操作添加成功!");
        }
        return ResultJson.fail("权限-操作添加失败!");
    }

    public ResultJson delete(PermissionOperation permissionOperation) {
        QueryWrapper<PermissionOperation> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(PermissionOperation::getPermissionId, permissionOperation.getPermissionId())
                .eq(PermissionOperation::getOperationId, permissionOperation.getOperationId());
        boolean flag = permissionOperationService.remove(queryWrapper);
        if (flag) {
            return ResultJson.successWithMsg("权限-菜单删除成功!");
        }
        return ResultJson.fail("权限-菜单删除失败!");
    }
}
