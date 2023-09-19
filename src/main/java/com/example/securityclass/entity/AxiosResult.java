package com.example.securityclass.entity;

public class AxiosResult<T> {
    private Integer code;
    private T data;
    private String msg;

    public AxiosResult() {
    }

    public AxiosResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public AxiosResult(Integer code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public AxiosResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public AxiosResult(T data, String msg) {
        this.data = data;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
