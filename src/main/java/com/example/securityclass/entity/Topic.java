package com.example.securityclass.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (Topic)实体类
 *
 * @author makejava
 * @since 2023-09-08 12:51:11
 */
public class Topic implements Serializable {
    private static final long serialVersionUID = 936904782359306722L;

    private Integer id;

    private String deviceid;

    private String subtopic;

    private String pubtopic;

    private String appid;

    private Date updatetime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getSubtopic() {
        return subtopic;
    }

    public void setSubtopic(String subtopic) {
        this.subtopic = subtopic;
    }

    public String getPubtopic() {
        return pubtopic;
    }

    public void setPubtopic(String pubtopic) {
        this.pubtopic = pubtopic;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

}

