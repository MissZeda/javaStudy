package com.example.securityclass.controller;


import com.example.securityclass.dto.UserDto;
import com.example.securityclass.entity.AxiosResult;
import com.example.securityclass.service.UserService;
import com.example.securityclass.util.MailUtils;
import com.example.securityclass.vo.UserVO;
import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/app/api")
public class AppController {

    @Resource
    private UserService userService;

    @Resource
    private MailUtils mailUtils;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("/hello")
    public String hello() {
        return "hello,这是一个公开的测试接口";
    }

    @PostMapping("/register")
    public AxiosResult<Map<String, Object>> register(@RequestBody UserDto user) {
        if (userService.register(user)) {
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

    @GetMapping("/code/{email}")
    public AxiosResult<Map<String, Integer>> getEmailCode(@PathVariable String email) throws MessagingException {
        int randomNumber = mailUtils.sendEmailCode(email);
        redisTemplate.opsForValue().set("mail:" + email, String.valueOf(randomNumber));
        redisTemplate.expire("mail:" + email, 180, TimeUnit.SECONDS);
        // 验证码写入redis
        return new AxiosResult<>(200, "短信发送成功");
    }
}
