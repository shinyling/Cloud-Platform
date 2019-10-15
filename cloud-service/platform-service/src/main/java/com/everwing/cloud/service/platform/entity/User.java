package com.everwing.cloud.service.platform.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author DELL shiny
 * @create 2019/10/14
 */
@Data
public class User {

    private String id;

    private String username;

    private String mobile;

    private String password;

    private Boolean status;

    private Boolean isLock;

    private Boolean isDelete;

    private LocalDateTime createTime;

    private String createBy;

    private LocalDateTime updateTime;

    private String updateBy;

    private String companyId;

    private Boolean type;

}
