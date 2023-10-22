package com.example.securityclass.mapper;

import com.example.securityclass.entity.SysPage;
import com.example.securityclass.entity.SysUser;
import com.example.securityclass.vo.UserMenuVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    SysUser loadUserByUsername(String username);

    List<String> queryAuthoritiesByUserId(int id);

    List<UserMenuVO> queryUserMenu(int id);

    int register(SysUser sysUser);

    List<String> queryPermissionByUrl(@Param("url") String requestURI);

    List<SysUser> queryAllUser();

    List<SysPage> queryUserPageByUserId(int id);

    boolean addNewUsersRole(int userId);

}
