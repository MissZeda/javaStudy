package com.example.securityclass.service;

import com.example.securityclass.entity.SysMenu;

import java.util.List;

public interface SystemService {
    boolean addNewInterface(SysMenu sysMenu);

    boolean deleteInterface(String id);

    List<SysMenu> getAllInterface();

    boolean distributeInterfaceAuthority();


}
