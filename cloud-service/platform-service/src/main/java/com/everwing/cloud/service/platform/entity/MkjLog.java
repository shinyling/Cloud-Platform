package com.everwing.cloud.service.platform.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author shiny
 * @since 2020-04-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mkj_log")
public class MkjLog implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 公司名称
     */
    private String companyId;

    /**
     * 项目名称
     */
    private String projectId;

    /**
     * 密码开门对应的房间code
     */
    private String tobuildingCode;

    private String formCode;

    /**
     * 触发开门的门口机编码
     */
    private String gatingCode;

    /**
     * 开门类型
     */
    private String type;

    /**
     * 门控机登录帐号
     */
    private String gatingAccount;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    private String frombuildingCode;


}
