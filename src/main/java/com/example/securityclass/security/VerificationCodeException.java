package com.example.securityclass.security;

import javax.security.sasl.AuthenticationException;


/**
 * 自定义验证码异常处理
 */
public class VerificationCodeException extends AuthenticationException {

    public VerificationCodeException() {
        super("图形验证码验证失败!");
    }
}