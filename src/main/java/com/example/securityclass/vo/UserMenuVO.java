package com.example.securityclass.vo;

import com.example.securityclass.entity.SysUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserMenuVO extends SysUser {
    private RoleVO roleVO;
}
