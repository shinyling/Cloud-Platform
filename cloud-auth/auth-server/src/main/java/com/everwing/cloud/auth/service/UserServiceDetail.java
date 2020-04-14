package com.everwing.cloud.auth.service;

import com.everwing.cloud.auth.dao.UserDao;
import com.everwing.cloud.auth.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author DELL shiny
 * @create 2019/5/21
 */
@Service
public class UserServiceDetail implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByMobile(username);
        if (user == null) {
            throw new UsernameNotFoundException("手机号不存在");
        }
        return user;
    }
}
