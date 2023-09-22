package com.example.securityclass.controller;


import com.example.securityclass.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/api")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/hello")
    public String hello() {
        return "hello，user";
    }

    @GetMapping("/list")
    public String list() {
        return "hello，user,list";
    }

}
