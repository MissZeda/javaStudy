package com.example.securityclass.controller;


import com.example.securityclass.entity.AxiosResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一的异常处理
 */
@ControllerAdvice
public class MyControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public AxiosResult<String> handlerException(Exception e) {
        //获取异常信息，存放如ResponseResult的msg属性
        String message = e.getMessage();
        //把ResponseResult作为返回值返回，要求到时候转换成json存入响应体中
        return new AxiosResult<>(50001, message);
    }
}