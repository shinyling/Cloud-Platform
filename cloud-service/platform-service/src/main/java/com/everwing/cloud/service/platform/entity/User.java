package com.everwing.cloud.service.platform.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.everwing.cloud.service.platform.enums.IsLockEnum;
import com.everwing.cloud.service.platform.vo.UserVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

/**
 * @author DELL shiny
 * @create 2019/10/14
 */
@Data
@TableName("user")
public class User {

    private String id;

    private String username;

    private String mobile;

    private String password;

    private Boolean status;

    private IsLockEnum isLock;

    @TableLogic(value = "false", delval = "true")
    private Boolean isDelete;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    private String createBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    private String updateBy;

    private String companyId;

    private Boolean type;

    public UserVo convertToUserVo() {
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(this, userVo);
        return userVo;
    }

}
