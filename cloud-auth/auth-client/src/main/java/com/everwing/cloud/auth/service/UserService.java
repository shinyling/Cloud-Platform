package com.everwing.cloud.auth.service;

import com.everwing.cloud.auth.dao.UserDao;
import com.everwing.cloud.auth.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author DELL shiny
 * @create 2019/5/21
 */
@Service
public class UserService {

    private static final BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();

    @Autowired
    private UserDao userDao;

    public User create(User user){
        user.setStatus(1);
        user.setCreateBy("sys");
        user.setIsDelete(0);
        user.setIsLock(0);
        String password=user.getPassword();
        user.setPassword(encoder.encode(password));
        return userDao.save(user);
    }
}
