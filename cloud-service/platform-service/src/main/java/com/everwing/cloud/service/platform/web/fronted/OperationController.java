package com.everwing.cloud.service.platform.web.fronted;


import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.service.platform.anno.SysLog;
import com.everwing.cloud.service.platform.biz.OperationBiz;
import com.everwing.cloud.service.platform.entity.Operation;
import com.everwing.cloud.service.platform.entity.Permission;
import com.everwing.cloud.service.platform.group.AddGroup;
import com.everwing.cloud.service.platform.param.PagedParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 操作
 *
 * @author shiny
 * @since 2019-12-05
 */
@Api(value = "操作", tags = "操作")
@RestController
@RequestMapping("/operation")
public class OperationController {

    @Autowired
    private OperationBiz operationBiz;

    @ApiOperation("新增操作")
    @PostMapping("add")
    @SysLog("新增操作")
    public ResultJson add(@Validated(AddGroup.class) @RequestBody Operation operation) {
        return operationBiz.add(operation);
    }

    @ApiOperation("删除操作")
    @DeleteMapping("delete/{id}")
    @SysLog("删除操作")
    public ResultJson delete(@PathVariable String id) {
        Assert.state(StringUtils.isNotBlank(id), "参数错误");
        return operationBiz.delete(id);
    }

    @ApiOperation("根据权限查询操作")
    @PostMapping("loadPageByPermissionId")
    @SysLog("根据权限查询操作")
    public ResultJson loadPageByPermissionId(@Validated @RequestBody PagedParam<Permission> pagedParam) {
        Assert.state(pagedParam.getPage().getCurrent() > 0, "分页参数错误!");
        Assert.state(pagedParam.getPage().getSize() > 0, "分页参数错误!");
        return operationBiz.loadPageByPermissionId(pagedParam);
    }
}
