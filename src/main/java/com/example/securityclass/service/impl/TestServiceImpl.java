package com.example.securityclass.service.impl;

import com.example.securityclass.entity.SysMenu;
import com.example.securityclass.mapper.TestMapper;
import com.example.securityclass.service.TestService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Resource
    private TestMapper testMapper;

    @Override
    public List<SysMenu> getMenuList() {
        return testMapper.getMenuList();
    }
}
