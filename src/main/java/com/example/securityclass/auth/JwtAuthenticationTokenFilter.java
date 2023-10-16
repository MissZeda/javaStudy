package com.example.securityclass.auth;

import com.example.securityclass.util.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * 这是一个JWT过滤器，每次请求时候都会走这一个过滤器，解析JWT
 */
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取JWT
        System.out.println("request = " + request);
        String token = request.getHeader("Authorization");
        System.out.println("--------->" + token);
        if (token != null) {
            JwtUtils.tokenVerify(token);
        }
        // 放行
        filterChain.doFilter(request, response);
    }
}