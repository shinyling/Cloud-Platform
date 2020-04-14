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
@TableName("project")
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String projectId;

    /**
     * 公司ID
     */
    private String companyId;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 项目地址
     */
    private String address;

    /**
     * 状态；参考t_sys_lookup表的enableDisable属性
     */
    private String status;

    /**
     * 项目经理
     */
    private String leader;

    /**
     * 账单自动生成开关: 0: 开启 , 1: 关闭
     */
    private Boolean billStatus;

    /**
     * 邮编号码
     */
    private String zipCode;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区/县
     */
    private String area;

    /**
     * 街道
     */
    private String streets;

    private String telephone;

    private String picture;

    /**
     * 创建人id
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

    /**
     * 客服电话
     */
    private String servicePhone;

    /**
     * 邻音二维码地址
     */
    private String linphoneQr;

    /**
     * 微信公众号二维码地址
     */
    private String wxQr;


}
