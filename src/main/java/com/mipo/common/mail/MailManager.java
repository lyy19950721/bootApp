package com.mipo.common.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailManager {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;//发件人

    /**
     * 简单的发送text格式的邮件
     * @param subject
     * @param context
     */
    public void sendTextMail(String subject, String context, String to){

        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(from);
            mailMessage.setTo(to);

            mailMessage.setSubject(subject);
            mailMessage.setText(context);

            mailSender.send(mailMessage);
        } catch (MailException e) {
            //这里处理一下
            e.printStackTrace();
        }

    }
}
