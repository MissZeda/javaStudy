package com.example.securityclass.controller;

import com.example.securityclass.entity.AxiosResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TestController {

    @RequestMapping("/test")
    public AxiosResult<Map<String, Object>> test() {
        return new AxiosResult<>(200, "success");
    }


}
