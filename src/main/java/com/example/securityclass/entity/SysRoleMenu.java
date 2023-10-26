package com.example.securityclass.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (SysRoleMenu)实体类
 *
 * @author makejava
 * @since 2023-09-08 12:51:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysRoleMenu implements Serializable {
    private static final long serialVersionUID = 965479037604186489L;
    /**
     * 角色ID
     */
    private Integer roleId;
    /**
     * 菜单id
     */
    private Integer menuId;



}

