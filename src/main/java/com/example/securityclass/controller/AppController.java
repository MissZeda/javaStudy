package com.example.securityclass.controller;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.example.securityclass.entity.AxiosResult;
import com.example.securityclass.service.UserService;
import com.example.securityclass.vo.UserVO;
import com.google.code.kaptcha.Producer;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/app/api")
public class AppController {

    @Resource
    private UserService userService;

    @Resource
    private Producer producer;

    @SneakyThrows
    @GetMapping("/captcha.jpg")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("image/jpeg");
        String capText = producer.createText();
        System.out.println("验证码:{}" + capText);
        request.getSession().setAttribute("captcha", capText);
        BufferedImage image = producer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();

        ImageIO.write(image, "jpg", out);
        out.flush();
    }

    @PostMapping("/register")
    public AxiosResult<Map<String, Object>> register(@RequestBody String userInfo) {
        JSONObject jsonObject = JSON.parseObject(userInfo);
        String username = jsonObject.getString("userName");
        String password = jsonObject.getString("password");
        if (userService.register(username, password)) {
            return new AxiosResult<>(200, null, "注册成功");
        }
        return new AxiosResult<>(200, null, "注册失败");
    }

    @GetMapping("/queryAuthoritiesByUserId/{id}")
    public AxiosResult<Map<String, Object>> queryAuthorities(@PathVariable String id) {
        List<String> authorities = userService.queryAuthoritiesByUserId(Integer.parseInt(id));
        Map<String, Object> map = new HashMap<>();
        map.put("authorities", authorities);
        return new AxiosResult<>(200, map, "查询成功");
    }

    @GetMapping("/queryUserRolesByUserId/{id}")
    public AxiosResult<Map<String, Object>> queryUserRolesByUserId(@PathVariable String id) {
        List<UserVO> roles = userService.queryUserRolesByUserId(Integer.parseInt(id));
        Map<String, Object> map = new HashMap<>();
        map.put("roles", roles);
        return new AxiosResult<>(200, map, "查询成功");
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }


}
