package com.everwing.cloud.service.platform.param;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 提供一个除了分页参数还可以传其他参数的类
 *
 * @author DELL shiny
 * @date 2020/3/16
 */
@Data
public class PagedParam<T> {

    private T t;

    @NotNull(message = "分页参数错误")
    private Page page;

    private String[] ascArr;

    private String[] descArr;

    public void setAscArr(String[] ascArr) {
        String[] underLineArr = new String[ascArr.length];
        for (int i = 0; i < ascArr.length; i++) {
            String underLine = StringUtils.camelToUnderline(ascArr[i]);
            underLineArr[i] = underLine;
        }
        this.ascArr = underLineArr;
    }

    public void setDescArr(String[] descArr) {
        String[] underLineArr = new String[descArr.length];
        for (int i = 0; i < descArr.length; i++) {
            String underLine = StringUtils.camelToUnderline(descArr[i]);
            underLineArr[i] = underLine;
        }
        this.descArr = underLineArr;
    }
}
