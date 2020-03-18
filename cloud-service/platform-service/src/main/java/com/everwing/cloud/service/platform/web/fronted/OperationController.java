package com.everwing.cloud.service.platform.web.fronted;


import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.service.platform.group.AddGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 *操作
 * @author shiny
 * @since 2019-12-05
 */
@Api(value = "操作",tags = "操作")
@RestController
@RequestMapping("/operation")
public class OperationController {

//    @Autowired
//    private OperationBiz operationBiz;
//
//    @ApiOperation("新增操作")
//    @PostMapping("add")
//    public ResultJson add(@Validated(AddGroup.class) @RequestBody Operation operation){
//
//    }
}
