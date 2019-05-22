package com.everwing.cloud.auth.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author DELL
 * @title: UserServiceTest
 * @projectName Cloud-Platform
 * @description: TODO
 * @date 2019/5/2117:09
 */
public class UserServiceTest {

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("secret"));
    }

}