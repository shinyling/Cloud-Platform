package com.everwing.cloud.service.platform.param;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * @author DELL shiny
 * @date 2020/3/16
 */
@Data
public class PagedParam<T> {

    private T t;

    private Page page;
}
