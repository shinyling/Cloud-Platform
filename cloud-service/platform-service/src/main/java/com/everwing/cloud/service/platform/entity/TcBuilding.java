package com.everwing.cloud.service.platform.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 如何标识：已入伙，未入伙
 * </p>
 *
 * @author shiny
 * @since 2020-04-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tc_building")
public class TcBuilding implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 建筑编号
     */
    private String buildingCode;

    /**
     * 父ID
     */
    private String pid;

    /**
     * 建筑名称
     */
    private String buildingName;

    /**
     * 建筑全名称
     */
    private String buildingFullName;

    private String projectId;

    private String buildingType;

    /**
     * 是否收费对象
     */
    private String isChargeObj;

    /**
     * 开门密码
     */
    private String password;

    /**
     * 建筑总面积
     */
    private BigDecimal buildingArea;

    /**
     * 套内面积
     */
    private BigDecimal usableArea;

    /**
     * 公摊面积
     */
    private BigDecimal shareArea;

    /**
     * 竣工面积
     */
    private BigDecimal finishArea;

    /**
     * 竣工日期
     */
    private LocalDate finishDate;

    /**
     * 用地总面积
     */
    private BigDecimal floorArea;

    /**
     * 房屋编码
     */
    private String houseCode;

    private String propertyName;

    private String propertyAddr;

    /**
     * 公司编码
     */
    private String companyId;

    private String joinFlag;

    /**
     * 入伙日期
     */
    private LocalDate joinDate;

    private String propertyRightType;

    private String propertyAttributes;

    private String propertyRightFlag;

    private String buildingCertificate;

    private String assetAttributes;

    /**
     * 销售状态
     */
    private String marketState;

    private String buildingHeight;

    private String discounts;

    private String parkingSpaceType;

    private String isStandardBuilding;

    private String assetUsage;

    private String placeAttribute;

    /**
     * 账单地址
     */
    private String billAddress;

    /**
     * 关联车位，当前车位是子母车位时，保存被关联的子母车位
     */
    private String associatedParkingSpaces;

    /**
     * 宗地id
     */
    private String isFixedParkingSpaces;

    /**
     * 创建用户id
     */
    private String createBy;

    /**
     * 最后更新用户id
     */
    private String updateBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 最后更新时间
     */
    private LocalDateTime modifyTime;


}
