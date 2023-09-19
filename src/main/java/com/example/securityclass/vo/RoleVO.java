package com.example.securityclass.vo;

import com.example.securityclass.entity.SysMenu;
import com.example.securityclass.entity.SysRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleVO {
    private SysMenu menuList;
    private SysRole role;
}
