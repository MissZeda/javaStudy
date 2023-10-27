package com.example.securityclass;

import com.example.securityclass.service.SystemService;
import com.example.securityclass.service.TestService;
import com.example.securityclass.service.UserService;
import com.example.securityclass.util.JwtUtils;
import com.example.securityclass.vo.UserMenuVO;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

@SpringBootTest
class SecurityclassApplicationTests {


    @Resource
    private TestService testService;

    @Resource
    private UserService userService;

    @Test
    void contextLoads() {
        List<UserMenuVO> userMenuVOS = userService.queryUserMenu(4);
        System.out.println("userMenuVOS = " + userMenuVOS);
    }

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Test
    void redisTest() {
      /*  System.out.println("testService = " + testService);
        redisTemplate.opsForValue().set("login:user:token2323", "123");*/
        String s = redisTemplate.opsForValue().get("l12231");
        System.out.println();
    }

    @Test
    void JWTTest() {
        String s = JwtUtils.parseToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJ7XCJhdXRoZW50aWNhdGVkXCI6dHJ1ZSxcImF1dGhvcml0aWVzXCI6W10sXCJkZXRhaWxzXCI6e1wicmVtb3RlQWRkcmVzc1wiOlwiMTI3LjAuMC4xXCJ9LFwibmFtZVwiOlwid29cIixcInByaW5jaXBhbFwiOntcImFjY291bnROb25FeHBpcmVkXCI6dHJ1ZSxcImFjY291bnROb25Mb2NrZWRcIjp0cnVlLFwiYXV0aG9yaXRpZXNcIjpbXSxcImNyZWRlbnRpYWxzTm9uRXhwaXJlZFwiOnRydWUsXCJkZWxGbGFnXCI6MCxcImVuYWJsZWRcIjp0cnVlLFwiaWRcIjo2LFwibmlja05hbWVcIjpcIk5VTExcIixcInBhc3N3b3JkXCI6XCJ7YmNyeXB0fSQyYSQxMCRCSGJrdnBncHVkeTdtV0lIUGl5d1B1YnBOa2YzNWM1Q2VXVExoZy54Qm1YbjYzYVplUG1BQ1wiLFwic3RhdHVzXCI6MCxcInVzZXJOYW1lXCI6XCJ3b1wiLFwidXNlclR5cGVcIjoxLFwidXNlcm5hbWVcIjpcIndvXCJ9fSIsImV4cCI6MTcwMDA0MDYwM30.WQ9P62OcDfapBddSPaBhkr6NxwEPn3FXU6k5bz1qHM0");
        System.out.println("s = " + s);
    }

    @Test
    void emailCodeTest() {
        SecureRandom secureRandom = new SecureRandom();
        // 生成一个6位随机数
        int min = 100000;
        int max = 999999;
        int randomNumber = secureRandom.nextInt(max - min + 1) + min;
        System.out.println("生成的随机数为: " + randomNumber);
    }


    @Test
    void test() {
        int numSamples = 100000;
        double mean = 50.0; // 均值
        double stdDeviation = 10.0; // 标准差

        Random random = new Random();
        for (int i = 0; i < numSamples; i++) {
            double randomValue = random.nextGaussian() * stdDeviation + mean;
            // 这里将随机数randomValue用于你的进一步处理或分析
            System.out.println(randomValue);
        }
    }

    @Resource
    private SystemService systemService;

    @Test
    void authorityTest() {
        systemService.distributeInterfaceAuthority();
    }


    /*@Resource
    private HistoryDataMapper historyDataMapper;

    @Test
    public void MybatisPlusTest(){
        Historydata historydata = historyDataMapper.selectById(1);
        System.out.println("historydata = " + historydata);
    }*/

}
