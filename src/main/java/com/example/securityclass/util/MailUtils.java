package com.example.securityclass.util;

import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class MailUtils {
    @Value("${spring.mail.username}")
    private String sendName;

    @Resource
    private JavaMailSender javaMailSender;

    public int sendEmailCode(String email) throws MessagingException {
        // 生成随机数作为验证码
        SecureRandom secureRandom = new SecureRandom();
        int randomNumber = secureRandom.nextInt(999999 - 100000 + 1) + 100000;
        // 发送验证码
        MimeMessage mMessage = javaMailSender.createMimeMessage();//创建邮件对象
        MimeMessageHelper mMessageHelper;
        mMessageHelper = new MimeMessageHelper(mMessage, true);
        mMessageHelper.setFrom(sendName);//发件人邮箱
        mMessageHelper.setTo(email);//收件人邮箱
        mMessageHelper.setSubject("注册验证码");
        mMessageHelper.setText("已收到您的注册请求，您的验证码为\n" + randomNumber + "\n请在三分钟内完成注册，此验证码的有效期为三分钟");
        javaMailSender.send(mMessage);//发送邮件
        return randomNumber;
    }
}
