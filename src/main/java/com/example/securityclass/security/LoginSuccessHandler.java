package com.example.securityclass.security;


import com.alibaba.fastjson2.JSON;
import com.example.securityclass.util.JwtUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录成功处理器
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String token = JwtUtils.token(authentication);
        response.addHeader("token", token);
        response.setContentType("application/json;charset=UTF-8");
        Map<String, Object> tokenInfo = new HashMap<>();
        tokenInfo.put("token", token);
        response.getWriter().write(JSON.toJSONString(tokenInfo));


    }
}
