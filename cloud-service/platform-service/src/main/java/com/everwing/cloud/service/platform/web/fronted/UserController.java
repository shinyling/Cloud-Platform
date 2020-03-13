package com.everwing.cloud.service.platform.web.fronted;

import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.common.exception.BusinessException;
import com.everwing.cloud.service.platform.biz.UserBiz;
import com.everwing.cloud.service.platform.group.AddGroup;
import com.everwing.cloud.service.platform.vo.AccountVo;
import com.everwing.cloud.service.platform.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Size;

/**
 * 用户管理
 * @author shiny
 * @date 2019-12-05
 */
@Api(value = "平台用户",tags = "平台用户")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserBiz userBiz;

    @ApiOperation(value = "通过账号加载平台用户信息")
    @GetMapping("load/{mobile}")
    public ResultJson loadByMobile(@PathVariable @Size(min = 8,max = 12,message = "电话号码验证失败!") String mobile) throws BusinessException {
        AccountVo accountVo=userBiz.queryUserInfo(mobile);
        return ResultJson.success(accountVo);
    }

    @ApiOperation(value = "平台用户注册")
    @PostMapping("register")
    public ResultJson register(@RequestBody @Validated(AddGroup.class) UserVo userVo){
        return userBiz.addUser(userVo);
    }

    @ApiOperation(value = "删除用户")
    @GetMapping("delete/{mobile:1[3456789]\\d{9}}")
    public ResultJson delete(@PathVariable String mobile){
        return userBiz.deleteUser(mobile);
    }

}
