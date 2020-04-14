package com.everwing.cloud.service.platform.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("building_gate")
public class BuildingGate implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 房屋id 关联Builidng_id
     */
    private String buildingId;

    /**
     * 门控机id
     */
    private String gateId;


}
