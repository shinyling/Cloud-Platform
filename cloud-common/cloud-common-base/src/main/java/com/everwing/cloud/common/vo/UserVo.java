package com.everwing.cloud.common.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * @author DELL shiny
 * @create 2019/11/29
 */
@Data
public class UserVo implements Serializable {

    private String id;

    private String username;

    private String companyId;

}
