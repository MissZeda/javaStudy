package com.example.securityclass.mapper;

import com.example.securityclass.entity.SysMenu;
import com.example.securityclass.entity.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SystemMapper {
    boolean addNewInterface(SysMenu sysMenus);

    boolean deleteInterface(String id);

    List<SysMenu> getAllInterface();

    List<SysRoleMenu> queryMenuWhichAdded();

    boolean distributeAuthority(List<SysMenu> sysMenus);
}
