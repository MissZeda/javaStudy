package com.example.securityclass.service;

import com.example.securityclass.dto.UserDto;
import com.example.securityclass.entity.SysPage;
import com.example.securityclass.entity.SysUser;
import com.example.securityclass.vo.UserMenuVO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @author YX
 * @date 2023/10/22
 */
public interface UserService extends UserDetailsService {
    List<String> queryAuthoritiesByUserId(int id);

    List<UserMenuVO> queryUserMenu(int id);

    boolean register(UserDto userDto);

    List<String> queryPermissionByUrl(String requestURI);

    List<SysUser> queryAllUser();

    List<SysPage> queryUserPageByUserId(int id);

    boolean addNewUsersRole(int userId);


}
