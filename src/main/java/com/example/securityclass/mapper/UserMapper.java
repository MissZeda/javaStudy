package com.example.securityclass.mapper;

import com.example.securityclass.entity.SysUser;
import com.example.securityclass.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    SysUser loadUserByUsername(String username);

    List<String> queryAuthoritiesByUserId(int id);

    List<UserVO> queryUserRolesByUserId(int id);

    boolean register(SysUser sysUser);

    List<String> queryPermissionByUrl(@Param("url") String requestURI);

    List<SysUser> queryAllUser();

}
