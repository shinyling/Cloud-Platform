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
@TableName("person_building")
public class PersonBuilding implements Serializable {

    private static final long serialVersionUID = 1L;

    private String personBuildingId;

    private String custId;

    private Integer state;

    private LocalDateTime relationDate;

    private String enterpriseId;

    private String buildingId;

    private String companyId;


}
