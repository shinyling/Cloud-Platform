package com.everwing.cloud.service.platform.param;

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
}
