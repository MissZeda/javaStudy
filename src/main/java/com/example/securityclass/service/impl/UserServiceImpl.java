package com.example.securityclass.service.impl;

import com.example.securityclass.entity.SysUser;
import com.example.securityclass.mapper.UserMapper;
import com.example.securityclass.service.UserService;
import com.example.securityclass.vo.UserVO;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UserServiceImpl implements UserService {


    @Resource
    private UserMapper userMapper;

    @Override
    public List<String> queryAuthoritiesByUserId(int id) {
        return userMapper.queryAuthoritiesByUserId(id);
    }

    @Override
    public List<UserVO> queryUserRolesByUserId(int id) {
        return userMapper.queryUserRolesByUserId(id);
    }

    @Override
    public boolean register(String username, String password) {
        String encodingID = "bcrypt";
        Map<String, PasswordEncoder> passwordEncoderMap = new HashMap<>();
        passwordEncoderMap.put(encodingID, new BCryptPasswordEncoder());
        PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder(encodingID, passwordEncoderMap);
        SysUser sysUser = new SysUser();
        sysUser.setUserName(username);
        sysUser.setPassword(passwordEncoder.encode(password));
        sysUser.setStatus(0);
        return userMapper.register(sysUser);
    }

    @Override
    public List<String> queryPermissionByUrl(String requestURI) {
        return userMapper.queryPermissionByUrl(requestURI);
    }

    @Override
    public List<SysUser> queryAllUser() {
        return userMapper.queryAllUser();
    }

    // 登录
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 用户名不存在
        SysUser sysUser = userMapper.loadUserByUsername(username);
        if (sysUser == null) {
            throw new BadCredentialsException("用户名不存在");
        }

        // 将数据库的用户的权限拆分成security要求的结构
        sysUser.setAuthorities(AuthorityUtils.createAuthorityList(queryAuthoritiesByUserId(sysUser.getId())));
        return sysUser;
    }
}
