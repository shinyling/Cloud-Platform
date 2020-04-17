package com.everwing.cloud.service.platform.vo;

import com.everwing.cloud.service.platform.entity.Menu;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author DELL shiny
 * @date 2020/4/17
 */
@Data
public class MenuTreeVo extends Menu implements Serializable {

    private List<MenuTreeVo> children;
}
