package com.example.securityclass.auth;

import com.example.securityclass.util.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取JWT
        String token = request.getHeader("Authorization");
        System.out.println("--------->" + token);
        if (token != null) {
            JwtUtils.tokenVerify(token);
        }
        filterChain.doFilter(request, response);
    }
}