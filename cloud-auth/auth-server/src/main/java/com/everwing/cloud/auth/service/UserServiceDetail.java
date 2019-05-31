package com.everwing.cloud.auth.service;

import com.everwing.cloud.auth.dao.UserDao;
import com.everwing.cloud.auth.entity.Role;
import com.everwing.cloud.auth.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        User user = userDao.findByName(username);
        Role role = new Role();
        role.setName("ROLE_USER");
        List<Role> authorities = new ArrayList<>();
        authorities.add(role);
        user.setAuthorities(authorities);

        if(user==null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        return user;
    }
}
