package com.everwing.cloud.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author DELL shiny
 * @create 2019/11/29
 */
@Data
public class UserVo implements Serializable {

    private String id;

    private String username;

    private String mobile;

    private String companyId;

}
