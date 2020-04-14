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
@TableName("account_information")
public class AccountInformation implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String accountId;

    private String accountCode;

    private String cardNum;

    private String name;

    private String mobile;

    private LocalDateTime createTime;


}
