package com.everwing.cloud.service.platform.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * APP软件升级包
 * </p>
 *
 * @author shiny
 * @since 2020-04-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("app_pkg")
public class AppPkg implements Serializable {

    private static final long serialVersionUID = 1L;

    private String appPkgId;

    private String version;

    private LocalDateTime uploadTime;

    private Integer status;

    /**
     * 版本类型：铃音,室内机，室内机分通用版和重庆版等等，门控机
0=IOS邻音 1=Android邻音 2=门控机 3=室内机 4=物业APP 5=室内机重庆
     */
    private Integer type;

    private LocalDateTime availableTime;

    private String md5;

    private String uploadAccountId;

    private String availableAccountId;

    private String pkgFileId;

    /**
     * 版本描述
     */
    private String description;

    private Boolean isForce;


}
