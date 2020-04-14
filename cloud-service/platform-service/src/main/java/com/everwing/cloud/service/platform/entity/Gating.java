package com.everwing.cloud.service.platform.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("gating")
public class Gating implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 设备id
     */
    private String equipmentNum;

    /**
     * 门控机账号
     */
    private String gatingCode;

    /**
     * 门控机名称
     */
    private String accountName;

    /**
     * 设备名称
     */
    private String equipmentName;

    /**
     * 设备型号
     */
    private String equipmentModel;

    /**
     * 设备sn
     */
    private String equipmentSn;

    /**
     * 批次编号
     */
    private String batchNummer;

    /**
     * 出厂日期
     */
    @TableField("Manufacture_date")
    private LocalDate manufactureDate;

    /**
     * 质保开始日期
     */
    private LocalDate qualityTimeStart;

    /**
     * 报废日期
     */
    private LocalDate scrapTime;

    /**
     * 报废原因
     */
    private String scrapCause;

    /**
     * 质保年限
     */
    private Integer qualityTerm;

    /**
     * 使用年限
     */
    private Integer employTerm;

    /**
     * 当前门控机的状态（未销售，已销售，正在使用，维修中，报废）
     */
    private String facilityState;

    /**
     * 销售合同ID
     */
    private String marketId;

    /**
     * 采购订单ID
     */
    private String purchaseId;

    /**
     * 生产厂商
     */
    private String productionFirm;

    /**
     * 生产地址
     */
    private String productionSite;

    /**
     * 位置
     */
    private String district;

    /**
     * 二维码
     */
    private String twoDimensionCode;

    /**
     * 门控机开门状态（1开门2关门）
     */
    private Boolean openGatingState;

    /**
     * 是否为围墙机(0不是1是)
     */
    private Integer isWallGating;

    /**
     * 所属项目名
     */
    private String employProject;

    /**
     * 项目id
     */
    private String projectId;

    /**
     * 公司id
     */
    private String companyId;

    private Boolean onlineState;

    private Boolean videosState;

    /**
     * 门控机当前版本
     */
    private String version;


}
