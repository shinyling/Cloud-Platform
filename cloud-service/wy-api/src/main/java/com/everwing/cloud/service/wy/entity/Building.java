package com.everwing.cloud.service.wy.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author shiny
 * @since 2019-05-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tc_building")
public class Building implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String buildingCode;

    private String pid;

    private String buildingName;

    private String buildingFullName;

    private String projectId;

    private String buildingType;

    private String isChargeObj;

    private String password;

    private BigDecimal buildingArea;

    private BigDecimal usableArea;

    private BigDecimal shareArea;

    private LocalDate finishDate;

    private BigDecimal floorArea;

    private String houseCode;

    private String houseCodeNew;

    private String propertyName;

    private String propertyAddr;

    private String companyId;

    private String joinFlag;

    private LocalDate joinDate;

    private BigDecimal unitWyPrice;

    private BigDecimal unitBtPrice;

    private String propertyRightType;

    private String propertyAttributes;

    private String propertyRightFlag;

    private String buildingCertificate;

    private String assetAttributes;

    private String marketState;

    private String buildingHeight;

    private String discounts;

    private String parkingSpaceType;

    private String isStandardBuilding;

    private String assetUsage;

    private String placeAttribute;

    private String billAddress;

    private String associatedParkingSpaces;

    private String isFixedParkingSpaces;

    private String createrId;

    private String createrName;

    private String modifyId;

    private String modifyName;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;

    private Integer isDelete;


}
