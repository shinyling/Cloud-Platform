package com.everwing.cloud.service.wy.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author DELL shiny
 * @create 2019/5/30
 */
@Data
public class BaseEntity implements Serializable {

    @TableField(exist = false)
    private String username;

    @TableField(exist = false)
    private String dataSource;

}
