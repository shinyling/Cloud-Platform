package com.everwing.cloud.service.platform.web.fronted;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.everwing.cloud.common.entity.ResultJson;
import com.everwing.cloud.common.exception.BusinessException;
import com.everwing.cloud.service.platform.biz.CompanyBiz;
import com.everwing.cloud.service.platform.group.AddGroup;
import com.everwing.cloud.service.platform.group.AuditCompanyGroup;
import com.everwing.cloud.service.platform.vo.CompanyVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author DELL shiny
 * @create 2019/12/10
 */
@Api(value = "公司", tags = "公司")
@RestController
@RequestMapping("company")
public class CompanyController {

    @Autowired
    private CompanyBiz companyBiz;

    @ApiOperation("注册公司")
    @PostMapping("register")
    public ResultJson register(@RequestBody @Validated(AddGroup.class) CompanyVo companyVo) {
        try {
            companyVo = companyBiz.add(companyVo);
        } catch (BusinessException e) {
            return ResultJson.fail(e.getMessage());
        }
        return ResultJson.success(companyVo);
    }

    @ApiOperation(value = "审核注册公司信息", notes = "公司ID为必传字段")
    @PostMapping("audit")
    public ResultJson audit(@RequestBody @Validated(AuditCompanyGroup.class) CompanyVo companyVo) {
        try {
            companyBiz.audit(companyVo);
        } catch (BusinessException e) {
            return ResultJson.fail("审核失败!");
        }
        return ResultJson.success("审核成功!");
    }

    @ApiOperation(value = "列出所有租户")
    @PostMapping("list")
    public ResultJson list(Page page) {
        IPage<Map<String, Object>> pageMap = companyBiz.listNames(page);
        return ResultJson.success(pageMap);
    }
}
