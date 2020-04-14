package com.everwing.cloud.service.platform.web.fronted;

import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.service.platform.utils.EnumUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DELL shiny
 * @date 2020/4/13
 */
@RestController
@RequestMapping("select")
public class SelectController {

    @GetMapping("isLock")
    public ResultJson loadIsLock() {
        return ResultJson.success(EnumUtil.convertLockEnumToSelectData());
    }
}
