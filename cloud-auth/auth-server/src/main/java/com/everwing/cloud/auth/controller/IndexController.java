package com.everwing.cloud.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author DELL shiny
 * @create 2020/3/3
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String indexPage(){
        return "index";
    }
}
