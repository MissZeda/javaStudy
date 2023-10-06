package com.example.securityclass.config;

import com.example.securityclass.entity.SysUser;
import com.example.securityclass.service.UserService;
import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;

@EnableScheduling//开启定时任务
@Component
@EnableAsync
public class ScheduleConfig {

    private static DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Resource
    private UserService userService;

    @Resource
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private static String sendName;


    //定时任务在每天的9:40分执行
    // @Scheduled(cron = "0 40 9 * * ?")

    @Async
    public void cron() throws MessagingException {
        createMail(javaMailSender, userService);
        System.out.println("发送邮件成功");
    }

    public static void createMail(JavaMailSender javaMailSender, UserService userService) throws MessagingException {
        MimeMessage mMessage = javaMailSender.createMimeMessage();//创建邮件对象
        MimeMessageHelper mMessageHelper;
        List<SysUser> sysUsers = userService.queryAllUser();
        Properties prop = new Properties();
        mMessageHelper = new MimeMessageHelper(mMessage, true);
        mMessageHelper.setFrom("19890534663@163.com");//发件人邮箱
        sysUsers.forEach(u -> {
            String email = u.getEmail();
            if (email == null) {
                return;
            }
            try {
                mMessageHelper.setTo(email);//收件人邮箱
                mMessageHelper.setSubject("会员卡即将到期提醒");//邮件的主题
                mMessageHelper.setText("会员卡即将到期提醒----------");
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
            javaMailSender.send(mMessage);//发送邮件
        });
    }

}
