package com.everwing.cloud.service.platform.biz;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.service.platform.entity.Operation;
import com.everwing.cloud.service.platform.entity.Permission;
import com.everwing.cloud.service.platform.entity.PermissionOperation;
import com.everwing.cloud.service.platform.param.PagedParam;
import com.everwing.cloud.service.platform.service.IOperationService;
import com.everwing.cloud.service.platform.service.IPermissionOperationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author DELL shiny
 * @date 2020/3/19
 */
@Slf4j
@Component
public class OperationBiz {

    @Autowired
    private IOperationService operationService;

    @Autowired
    private IPermissionOperationService permissionOperationService;

    public ResultJson add(Operation operation) {
        QueryWrapper<Operation> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Operation::getName, operation.getName());
        Operation stored = operationService.getOne(queryWrapper);
        if (stored != null) {
            return ResultJson.fail("此操作名称已存在，新增失败!");
        }
        boolean flag = operationService.save(operation);
        if (flag) {
            return ResultJson.successWithMsg("新增操作成功!");
        }
        return ResultJson.fail("新增操作失败!");
    }

    public ResultJson delete(String id) {
        boolean flag = operationService.removeById(id);
        if (flag) {
            return ResultJson.successWithMsg("删除操作成功!");
        }
        return ResultJson.fail("删除操作失败!");
    }

    public ResultJson loadPageByPermissionId(PagedParam<Permission> pagedParam) {
        Page page = pagedParam.getPage();
        Permission permission = pagedParam.getT();
        if (permission != null) {
            String permissionId = permission.getId();
            if (StringUtils.isNotBlank(permissionId)) {
                QueryWrapper<PermissionOperation> queryWrapper = new QueryWrapper<>();
                queryWrapper.lambda().eq(PermissionOperation::getPermissionId, permissionId);
                List<PermissionOperation> operationList = permissionOperationService.list(queryWrapper);
                if (CollUtil.isNotEmpty(operationList)) {
                    List<String> operationIds = operationList.stream().map(permissionOperation -> permissionOperation.getOperationId())
                            .collect(Collectors.toList());
                    if (CollUtil.isNotEmpty(operationIds)) {
                        QueryWrapper<Operation> operationQueryWrapper = new QueryWrapper<>();
                        operationQueryWrapper.lambda().in(Operation::getId, operationIds);
                        return ResultJson.success(operationService.page(page, operationQueryWrapper));
                    }
                }
            }
        }
        return ResultJson.success(Collections.emptyList());
    }
}
