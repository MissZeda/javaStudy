package com.example.securityclass;

import com.example.securityclass.entity.SysMenu;
import com.example.securityclass.service.TestService;
import com.example.securityclass.service.UserService;
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
        redisTemplate.opsForValue().set("name1", "123");
        System.out.println(redisTemplate.opsForValue().get("name1"));
    }
}
