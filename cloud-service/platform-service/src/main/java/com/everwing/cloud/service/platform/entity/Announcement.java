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
@TableName("announcement")
public class Announcement implements Serializable {

    private static final long serialVersionUID = 1L;

    private String announcementId;

    private String title;

    private String content;

    /**
     * all=所有
     */
    private String targetCompanyId;

    /**
     * 创建人账号
     */
    private String createAccountId;

    private LocalDateTime createDate;

    /**
     * 0=未发布 1=已发布
     */
    private Integer status;

    /**
     * 是否已阅读
     */
    private Boolean isReaded;


}
