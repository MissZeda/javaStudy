package com.example.securityclass.entity;

import java.io.Serializable;

/**
 * (SysRoleMenu)实体类
 *
 * @author makejava
 * @since 2023-09-08 12:51:10
 */
public class SysRoleMenu implements Serializable {
    private static final long serialVersionUID = 965479037604186489L;
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 菜单id
     */
    private Long menuId;


    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

}

