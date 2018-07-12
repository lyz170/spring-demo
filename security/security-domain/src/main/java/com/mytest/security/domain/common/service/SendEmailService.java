package com.mytest.security.domain.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/9/28.
 */
@Service
public class SendEmailService {

    @Value("${email.from}")
    private String emailFrom;

    @Value("${seccode.verify.minutes}")
    private String verifyMinutes;

    @Autowired
    JavaMailSender mailSender;

    public void sendSecCodeMail(String emailTo, String secCode) {
        String subject = "来自Security System的验证码";
        String text = "您的验证码是：" + secCode + "。请在" + verifyMinutes + "分钟内通过验证！";
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(emailFrom);
        mail.setTo(emailTo);
        mail.setSubject(subject);
        mail.setText(text);
        mailSender.send(mail);
    }
}
