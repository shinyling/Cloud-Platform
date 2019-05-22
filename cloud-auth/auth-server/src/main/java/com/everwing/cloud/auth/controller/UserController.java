package com.everwing.cloud.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author DELL shiny
 * @create 2019/5/21
 */
@RestController
@RequestMapping("/users")
public class UserController {

    /**
     * 暴露Remote Token Services接口
     * 如果其它服务需要验证Token，则需要远程调用授权服务暴露的验证Token的API接口
     * @param principal
     * @return
     */
    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public Principal getUser(Principal principal) {
        return principal;
    }
}
