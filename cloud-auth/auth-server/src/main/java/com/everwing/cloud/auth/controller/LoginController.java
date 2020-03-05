package com.everwing.cloud.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author DELL shiny
 * @create 2020/3/3
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage(){
        return "base-login";
    }
}
