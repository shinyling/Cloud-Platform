package com.everwing.cloud.service.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.everwing.cloud.service.platform.vo.Company;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author shiny
 * @since 2019-05-07
 */
@Repository
public interface CompanyMapper extends BaseMapper<Company> {

    void createSchema(@Param("dbName") String dbName, @Param("username") String username,@Param("password") String password);
}
