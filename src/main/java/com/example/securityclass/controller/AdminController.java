package com.example.securityclass.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/api")
public class AdminController {

    @GetMapping("/hello")
    public String hello(String username) {
        return "hello，admin";
    }

    @GetMapping("/test")
    public String test(String username) {
        return "hello，admin,test";
    }
}
