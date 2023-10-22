package com.example.securityclass.entity;

import java.io.Serializable;

/**
 * (SysPage)实体类
 *
 * @author makejava
 * @since 2023-10-22 20:36:52
 */
public class SysPage implements Serializable {
    private static final long serialVersionUID = -38840498482075159L;

    private Integer id;

    private String routerUrl;

    private Object permission;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRouterUrl() {
        return routerUrl;
    }

    public void setRouterUrl(String routerUrl) {
        this.routerUrl = routerUrl;
    }

    public Object getPermission() {
        return permission;
    }

    public void setPermission(Object permission) {
        this.permission = permission;
    }

}

