package com.example.securityclass.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (Device)实体类
 *
 * @author makejava
 * @since 2023-09-08 12:51:10
 */
public class Device implements Serializable {
    private static final long serialVersionUID = 463792434247305558L;

    private Integer id;

    private String name;

    private String type;

    private String description;

    private Date createtime;

    private Integer usercount;


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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getUsercount() {
        return usercount;
    }

    public void setUsercount(Integer usercount) {
        this.usercount = usercount;
    }

}

