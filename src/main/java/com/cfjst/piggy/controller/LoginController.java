package com.cfjst.piggy.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



/**
 * 登录请求
 */


@Controller
public class LoginController {

    @RequestMapping("/login")
    public String hello()  {
        return "login";
    }
    
}
