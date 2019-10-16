package com.everwing.cloud.service.wy.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author DELL shiny
 * @create 2019/10/16
 */
@Data
public class BuildingVo implements Serializable {

    private String id;

    private String houseCode;

    private String buildingFullName;
}
