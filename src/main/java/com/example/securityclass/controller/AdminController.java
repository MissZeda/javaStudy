package com.example.securityclass.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * admin用户才可以访问的接口
 */
@RestController
@RequestMapping("/admin/api")
public class AdminController {


}
