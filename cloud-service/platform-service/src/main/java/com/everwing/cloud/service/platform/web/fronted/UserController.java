package com.everwing.cloud.service.platform.web.fronted;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.common.exception.BusinessException;
import com.everwing.cloud.service.platform.biz.UserBiz;
import com.everwing.cloud.service.platform.vo.AccountVo;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author shiny
 * @since 2019-12-05
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserBiz userBiz;

    @GetMapping("mobile/{mobile}")
    public ResultJson<AccountVo> loadByMobile(@PathVariable String mobile) throws BusinessException {
        if(StringUtils.isBlank(mobile)){
            return ResultJson.fail("参数错误");
        }
        AccountVo accountVo=userBiz.queryUserInfo(mobile);
        return ResultJson.success(accountVo);
    }

}
