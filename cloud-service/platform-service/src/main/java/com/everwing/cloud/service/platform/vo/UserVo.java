package com.everwing.cloud.service.platform.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author DELL shiny
 * @create 2019/12/5
 */
@Data
public class UserVo implements Serializable {

    private String id;

    private String username;

    private String mobile;

}
