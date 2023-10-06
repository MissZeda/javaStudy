package com.example.securityclass.controller;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.example.securityclass.config.ScheduleConfig;
import com.example.securityclass.entity.AxiosResult;
import com.example.securityclass.entity.Device;
import com.example.securityclass.service.UserService;
import com.example.securityclass.task.AsyncTask;
import com.example.securityclass.vo.UserVO;
import com.google.code.kaptcha.Producer;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/app/api")
public class AppController {

    @Resource
    private UserService userService;

    @Resource
    private Producer producer;

    @Value("${spring.mail.username}")
    private String sendName;

    @Resource
    private JavaMailSender javaMailSender;

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


    @SneakyThrows
    @RequestMapping("send")
    public AxiosResult<String> sendMail03() {
        ScheduleConfig.createMail(javaMailSender, userService);
        return new AxiosResult<>(0, "邮件发送成功", null);
    }

    @Resource
    private AsyncTask asyncTask;

    @RequestMapping("/asyncfuture")
    public AxiosResult<String> asyncfuture() throws InterruptedException {
        long begin = System.currentTimeMillis();
        Future<String> task4 = asyncTask.task4();
        Future<String> task5 = asyncTask.task5();
        Future<String> task6 = asyncTask.task6();
        for (; ; ) {
            if (task4.isDone() && task5.isDone() && task6.isDone()) {
                break;
            }
        }
        long end = System.currentTimeMillis();
        long total = end - begin;
        System.out.println("总的执行时间=" + total);
        return new AxiosResult<>(0, "总的执行时间=" + total, null);
    }

    @RequestMapping("/futureDevice")
    public AxiosResult<List<Device>> futureDevice() throws InterruptedException, ExecutionException {
        long begin = System.currentTimeMillis();
        CompletableFuture<AxiosResult<List<Device>>> task7 = asyncTask.task7();
        for (; ; ) {
            if (task7.isDone()) {
                break;
            }
        }
        long end = System.currentTimeMillis();
        long total = end - begin;
        System.out.println("总的执行时间=" + total);
        return task7.get();
    }


    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }


}
