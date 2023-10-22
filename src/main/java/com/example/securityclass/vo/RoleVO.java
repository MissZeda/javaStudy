package com.example.securityclass.vo;

import com.example.securityclass.entity.SysMenu;
import com.example.securityclass.entity.SysRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
public class RoleVO extends SysRole {
    @Getter
    @Setter
    private List<SysMenu> menuList;
}
