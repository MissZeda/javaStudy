package com.example.securityclass.entity;

import java.io.Serializable;

/**
 * 角色表(SysRole)实体类
 *
 * @author makejava
 * @since 2023-09-08 12:51:10
 */
public class SysRole implements Serializable {
    private static final long serialVersionUID = 403366378077722916L;

    private Integer id;

    private String name;
    /**
     * 角色权限字符串
     */
    private String roleKey;
    /**
     * 角色状态（0正常 1停用）
     */
    private Integer status;
    /**
     * del_flag
     */
    private Integer delFlag;
    /**
     * 备注
     */
    private String remark;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}

