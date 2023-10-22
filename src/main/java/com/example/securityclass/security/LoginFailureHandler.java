package com.example.securityclass.security;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errMessage = exception.getMessage();
        response.setContentType("application/json;charset=UTF-8");
        Map<String, Object> errInfo = new HashMap<>();
        errInfo.put("msg", errMessage);
        errInfo.put("code", 50001);
        response.getWriter().write(JSON.toJSONString(errInfo));
    }
}
