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
@TableName("ly_authorization_account")
public class LyAuthorizationAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 授权人账户ID
     */
    private String authorizedAccountId;

    /**
     * 被授权人账户ID
     */
    private String authorizeeAccountId;

    /**
     * 授权开始时间
     */
    private LocalDateTime startTime;

    /**
     * 授权结束时间
     */
    private LocalDateTime endTime;

    /**
     * 授权建筑结构ID，以逗号分隔
     */
    private String buildIds;


}
