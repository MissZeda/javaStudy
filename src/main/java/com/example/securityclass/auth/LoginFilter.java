package com.example.securityclass.auth;


import com.alibaba.fastjson2.JSON;
import com.example.securityclass.entity.SysUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * 登录过滤器，继承UsernamePasswordAuthenticationFilter抽象类，实现attemptAuthentication方法
 */
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    // 默认登录路径，可以改
    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher("/login",
            "POST");

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        // 默认POST方法登录，不符合抛出异常
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        // 获得通过request的字符流读取获得用户
        SysUser sysUser = obtainUser(request);
        // 给需要认证的用户填入信息，把pwd和username放进去
        UsernamePasswordAuthenticationToken authRequest = UsernamePasswordAuthenticationToken.unauthenticated(sysUser.getUserName(),
                sysUser.getPassword());

        setDetails(request, authRequest);
        // 进行下一步认证管理
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    private SysUser obtainUser(HttpServletRequest request) throws IOException {
        // 获取前端传递过来的用户名和密码然后进行校验，这里因为前端发送过来的是Json字符串，所以需要request获取reader流得到数据
        // 然后将json字符串转换为Bean对象。之后返回给Security校验即可
        String line = null;
        // 从请求体获取到数据
        BufferedReader reader = request.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        if ((line = reader.readLine()) != null) {
            // 将读取到的字符串添加进sb
            stringBuilder.append(line);
        }
        // 字符串转换
        SysUser sysUser = JSON.parseObject(stringBuilder.toString(), SysUser.class);
        return sysUser;
    }
}
