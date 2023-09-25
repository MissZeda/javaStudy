package com.example.securityclass.controller;


import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/api")
public class AdminController {

    @GetMapping("/hello")
    // 你扣钱
    @Transactional
    public String hello(String username) {
        return "hello，admin";
    }

    // 卖家加钱
    @Transactional
    @GetMapping("/test")
    public String test(String username) {
        return "hello，admin,test";
    }


    @GetMapping("/device")
    public String device() {
        return "hello，admin,device";
    }
}
