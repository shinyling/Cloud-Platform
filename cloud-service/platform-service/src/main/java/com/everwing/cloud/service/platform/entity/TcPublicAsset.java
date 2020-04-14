package com.everwing.cloud.service.platform.entity;

import java.math.BigDecimal;
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
@TableName("tc_public_asset")
public class TcPublicAsset implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String buildingCode;

    private String houseCode;

    private String buildingFullName;

    private String description;

    private String address;

    private String location;

    /**
     * 数量
     */
    private Integer amount;

    private String unit;

    private String isHold;

    private String purpose;

    private String isManage;

    private String isBilling;

    private String isWater;

    private String isElectricity;

    private BigDecimal unitWyPrice;

    private BigDecimal unitBtPrice;

    private Integer waterAmount;

    private Integer electricityAmount;

    private String projectId;

    private String createBy;

    private String updateBy;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;

    private Integer isDelete;


}
