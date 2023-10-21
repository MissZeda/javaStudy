package com.example.securityclass.service;

import com.example.securityclass.dto.UserDto;
import com.example.securityclass.entity.SysUser;
import com.example.securityclass.vo.UserVO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<String> queryAuthoritiesByUserId(int id);

    List<UserVO> queryUserRolesByUserId(int id);

    boolean register(UserDto userDto);

    List<String> queryPermissionByUrl(String requestURI);

    List<SysUser> queryAllUser();
}
