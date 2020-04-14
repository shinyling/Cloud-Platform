package com.everwing.cloud.service.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.everwing.cloud.service.platform.entity.Company;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author shiny
 * @since 2020-04-07
 */
public interface CompanyMapper extends BaseMapper<Company> {

    boolean createSchema(@Param("dbName") String dbName, @Param("username") String username, @Param("password") String password);
}
