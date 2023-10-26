package com.example.securityclass.service.impl;

import com.example.securityclass.entity.SysMenu;
import com.example.securityclass.entity.SysRoleMenu;
import com.example.securityclass.mapper.SystemMapper;
import com.example.securityclass.service.SystemService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemServiceImpl implements SystemService {

    @Resource
    private SystemMapper systemMapper;


    @Override
    public boolean addNewInterface(SysMenu sysMenu) {
        return systemMapper.addNewInterface(sysMenu);
    }

    @Override
    public boolean deleteInterface(String id) {
        return systemMapper.deleteInterface(id);
    }

    @Override
    public List<SysMenu> getAllInterface() {
        return systemMapper.getAllInterface();
    }

    @Override
    public boolean distributeInterfaceAuthority() {
        // 所有的接口
        List<SysMenu> systemInterfaces = getAllInterface();
        // 接口的path以/admin开头添加给role_menu的roleId为1的，否则就添加给roleId为2的
        // 添加之前要先查看menuId有没有已经添加过的接口，已经添加过的接口就不要添加了
        List<SysRoleMenu> menus = systemMapper.queryMenuWhichAdded();
        List<SysMenu> awaitAddInterface = systemInterfaces.stream()
                .filter(systemInterface -> menus.stream()
                        .noneMatch(menu -> menu.getMenuId().equals(systemInterface.getId())))
                .toList();
        List<SysMenu> adminAPI = awaitAddInterface.stream().filter(e -> e.getPath().startsWith("/admin/api")).toList();
        List<SysMenu> userAPI = awaitAddInterface.stream().filter(e -> e.getPath().startsWith("/user/api")).toList();
        try {
            systemMapper.distributeAuthority(adminAPI);
            systemMapper.distributeAuthority(userAPI);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
