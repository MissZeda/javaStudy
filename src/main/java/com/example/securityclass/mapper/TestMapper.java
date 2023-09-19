package com.example.securityclass.mapper;

import com.example.securityclass.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TestMapper {
    List<SysMenu> getMenuList();
}
