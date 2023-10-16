package com.example.securityclass;

import com.example.securityclass.entity.SysMenu;
import com.example.securityclass.service.TestService;
import com.example.securityclass.service.UserService;
import com.example.securityclass.util.JwtUtils;
import com.example.securityclass.vo.UserVO;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@SpringBootTest
class SecurityclassApplicationTests {


    @Resource
    private TestService testService;

    @Resource
    private UserService userService;

    @Test
    void contextLoads() {
        List<SysMenu> menuList = testService.getMenuList();

        System.out.println("menuList = " + menuList);
    }

    @Test
    void testUser() {
        List<UserVO> userVOS = userService.queryUserRolesByUserId(1);
        System.out.println("userVOS = " + userVOS);
    }


    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Test
    void redisTest() {
        System.out.println("testService = " + testService);
        redisTemplate.opsForValue().set("login:user:token2323", "123");
        System.out.println(redisTemplate.opsForValue().get("login:user:token2323"));
    }

    @Test
    void JWTTest() {
        String s = JwtUtils.parseToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJ7XCJhdXRoZW50aWNhdGVkXCI6dHJ1ZSxcImF1dGhvcml0aWVzXCI6W10sXCJkZXRhaWxzXCI6e1wicmVtb3RlQWRkcmVzc1wiOlwiMTI3LjAuMC4xXCJ9LFwibmFtZVwiOlwid29cIixcInByaW5jaXBhbFwiOntcImFjY291bnROb25FeHBpcmVkXCI6dHJ1ZSxcImFjY291bnROb25Mb2NrZWRcIjp0cnVlLFwiYXV0aG9yaXRpZXNcIjpbXSxcImNyZWRlbnRpYWxzTm9uRXhwaXJlZFwiOnRydWUsXCJkZWxGbGFnXCI6MCxcImVuYWJsZWRcIjp0cnVlLFwiaWRcIjo2LFwibmlja05hbWVcIjpcIk5VTExcIixcInBhc3N3b3JkXCI6XCJ7YmNyeXB0fSQyYSQxMCRCSGJrdnBncHVkeTdtV0lIUGl5d1B1YnBOa2YzNWM1Q2VXVExoZy54Qm1YbjYzYVplUG1BQ1wiLFwic3RhdHVzXCI6MCxcInVzZXJOYW1lXCI6XCJ3b1wiLFwidXNlclR5cGVcIjoxLFwidXNlcm5hbWVcIjpcIndvXCJ9fSIsImV4cCI6MTcwMDA0MDYwM30.WQ9P62OcDfapBddSPaBhkr6NxwEPn3FXU6k5bz1qHM0");
        System.out.println("s = " + s);
    }
}
