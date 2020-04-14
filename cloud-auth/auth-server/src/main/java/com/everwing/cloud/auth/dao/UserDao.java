package com.everwing.cloud.auth.dao;

import com.everwing.cloud.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author DELL shiny
 * @create 2019/5/21
 */
public interface UserDao extends JpaRepository<User, Long> {

    User findByMobile(String mobile);
}
