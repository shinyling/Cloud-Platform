package com.everwing.cloud.auth.controller;

import com.everwing.cloud.auth.entity.User;
import com.everwing.cloud.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author DELL shiny
 * @create 2019/5/21
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("registry")
    public User createUser(@RequestBody User user){
        return userService.create(user);
    }

    @GetMapping("list")
    public List<User> list(){
        return userService.list();
    }
}
