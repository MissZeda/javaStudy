package com.example.securityclass.entity;


import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * (Data)实体类
 *
 * @author makejava
 * @since 2023-09-08 12:51:10
 */
@lombok.Data
public class Data implements Serializable {
    @Serial
    private static final long serialVersionUID = 976825661961654730L;


    private Integer id;

    private String name;

    private Double temperature;

    private Double humidity;

    private Double light;

    private Date time;

    private String memo;


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

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getLight() {
        return light;
    }

    public void setLight(Double light) {
        this.light = light;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

}

