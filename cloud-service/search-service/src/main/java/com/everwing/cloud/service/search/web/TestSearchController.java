package com.everwing.cloud.service.search.web;

import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.service.search.entity.AccountSO;
import com.everwing.cloud.service.search.service.AccountService;
import com.everwing.cloud.service.search.service.CoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DELL shiny
 * @create 2020/3/9
 */
@Api(value = "搜索", tags = "搜索")
@RestController
public class TestSearchController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private CoreService coreService;

    @ApiOperation(value = "搜索", tags = "搜索")
    @PostMapping(value = "search")
    public ResultJson testSearch(@RequestBody AccountSO accountSO) {
        accountService.addAccount(accountSO);
        return ResultJson.success(null);
    }

    @ApiOperation(value = "添加core", tags = "添加core")
    @GetMapping(value = "addCore")
    public ResultJson addCore() {
        boolean flag = coreService.addCore();
        return ResultJson.success(flag);
    }
}
