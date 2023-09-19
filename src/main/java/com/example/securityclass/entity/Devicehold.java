package com.example.securityclass.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (Devicehold)实体类
 *
 * @author makejava
 * @since 2023-09-08 12:51:10
 */
public class Devicehold implements Serializable {
    private static final long serialVersionUID = 758559553311917884L;

    private Integer id;

    private String username;

    private String devname;

    private Date term;

    private Integer holded;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDevname() {
        return devname;
    }

    public void setDevname(String devname) {
        this.devname = devname;
    }

    public Date getTerm() {
        return term;
    }

    public void setTerm(Date term) {
        this.term = term;
    }

    public Integer getHolded() {
        return holded;
    }

    public void setHolded(Integer holded) {
        this.holded = holded;
    }

}

