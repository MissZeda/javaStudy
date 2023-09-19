package com.example.securityclass.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (Oprecord)实体类
 *
 * @author makejava
 * @since 2023-09-08 12:51:10
 */
public class Oprecord implements Serializable {
    private static final long serialVersionUID = -95462641457655678L;

    private Integer id;

    private String name;

    private String cmdtype;

    private String subdevname;

    private Integer unit;

    private Integer act;

    private String content;

    private Date sendtime;

    private String username;


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

    public String getCmdtype() {
        return cmdtype;
    }

    public void setCmdtype(String cmdtype) {
        this.cmdtype = cmdtype;
    }

    public String getSubdevname() {
        return subdevname;
    }

    public void setSubdevname(String subdevname) {
        this.subdevname = subdevname;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Integer getAct() {
        return act;
    }

    public void setAct(Integer act) {
        this.act = act;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}

