package com.everwing.cloud.service.platform.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.everwing.cloud.service.platform.vo.UserVo;
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

    private Boolean isLock;

    @TableLogic(value = "false", delval = "true")
    private Boolean isDelete;

    private LocalDateTime createTime;

    private String createBy;

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
