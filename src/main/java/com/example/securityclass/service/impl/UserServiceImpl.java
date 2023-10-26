package com.example.securityclass.service.impl;

import com.example.securityclass.dto.UserDto;
import com.example.securityclass.entity.SysPage;
import com.example.securityclass.entity.SysUser;
import com.example.securityclass.mapper.UserMapper;
import com.example.securityclass.service.UserService;
import com.example.securityclass.vo.UserMenuVO;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
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

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public List<String> queryAuthoritiesByUserId(int id) {
        return userMapper.queryAuthoritiesByUserId(id);
    }

    @Override
    public List<UserMenuVO> queryUserMenu(int id) {
        return userMapper.queryUserMenu(id);
    }

    @Override
    public boolean register(UserDto userDto) {
        // 存储的验证码
        String mailCode = redisTemplate.opsForValue().get("mail:" + userDto.getEmail());
        System.out.println("mailCode = " + mailCode);
        if (mailCode == null) {
            throw new RuntimeException("输入的验证码有误或邮箱不匹配");
        }
        if (!mailCode.equals(userDto.getCode())) {
            throw new RuntimeException("验证码有误！");
        }
        String encodingID = "bcrypt";
        Map<String, PasswordEncoder> passwordEncoderMap = new HashMap<>();
        passwordEncoderMap.put(encodingID, new BCryptPasswordEncoder());
        PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder(encodingID, passwordEncoderMap);
        SysUser sysUser = new SysUser();
        sysUser.setUserName(userDto.getUserName());
        sysUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        sysUser.setStatus(0);
        sysUser.setEmail(userDto.getEmail());
        sysUser.setSex(userDto.getSex());
        sysUser.setPhonenumber(userDto.getPhonenumber());
        int id = userMapper.register(sysUser);
        if (id >= 1) {
            return addNewUsersRole(sysUser.getId());
        }
        return false;
    }

    @Override
    public List<String> queryPermissionByUrl(String requestURI) {
        return userMapper.queryPermissionByUrl(requestURI);
    }

    @Override
    public List<SysUser> queryAllUser() {
        return userMapper.queryAllUser();
    }

    @Override
    public List<SysPage> queryUserPageByUserId(int id) {
        return userMapper.queryUserPageByUserId(id);
    }

    @Override
    public boolean addNewUsersRole(int userId) {
        return userMapper.addNewUsersRole(userId);
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
