package com.example.securityclass.controller;


import com.example.securityclass.dto.UserDto;
import com.example.securityclass.entity.AxiosResult;
import com.example.securityclass.service.UserService;
import com.example.securityclass.util.MailUtils;
import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * 不用校验权限的公共接口
 *
 * @author YX
 * @date 2023/10/22
 */
@RestController
@RequestMapping("/app/api")
public class AppController {

    @Resource
    private UserService userService;

    @Resource
    private MailUtils mailUtils;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 注册
     *
     * @param user 用户
     * @return {@link AxiosResult}<{@link Map}<{@link String}, {@link Object}>>
     */
    @PostMapping("/register")
    public AxiosResult<Map<String, Object>> register(@RequestBody UserDto user) {
        if (userService.register(user)) {
            System.out.println("user = " + user.getId());
            return new AxiosResult<>(200, null, "注册成功");
        }
        return new AxiosResult<>(200, null, "注册失败");
    }

    /**
     * 获取电子邮件代码
     *
     * @param email 电子邮件
     * @return {@link AxiosResult}<{@link Map}<{@link String}, {@link Integer}>>
     * @throws MessagingException 消息传递异常
     */
    @GetMapping("/code/{email}")
    public AxiosResult<Map<String, Integer>> getEmailCode(@PathVariable String email) throws MessagingException {
        int randomNumber = mailUtils.sendEmailCode(email);
        redisTemplate.opsForValue().set("mail:" + email, String.valueOf(randomNumber));
        redisTemplate.expire("mail:" + email, 180, TimeUnit.SECONDS);
        // 验证码写入redis
        return new AxiosResult<>(200, "短信发送成功");
    }


}
