package com.everwing.cloud.service.platform.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 身份认证表
 * </p>
 *
 * @author shiny
 * @since 2020-04-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("account_identity")
public class AccountIdentity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String accountId;

    /**
     * 证件号
     */
    private String identityCode;

    /**
     * 证件类型, 0=身份证 1=护照 2=其他
     */
    private Integer identityType;

    /**
     * 真实姓名
     */
    private String realName;

    private String mobile;

    private LocalDateTime createTime;

    private LocalDateTime birthday;

    private String email;

    /**
     * 多文件id，用逗号隔开
     */
    private String identityFileIds;

    /**
     * 性别 false-0=女 true-1=男
     */
    private Boolean sex;


}
