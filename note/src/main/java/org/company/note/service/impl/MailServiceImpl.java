package org.company.note.service.impl;

import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.SneakyThrows;
import org.company.note.service.MailService;
import org.company.note.utils.Const;
//import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;

//import com.my.demo.project.service.MailService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.company.note.utils.FlowUtils;

import java.util.Random;
import java.util.concurrent.TimeUnit;
//import org.thymeleaf.TemplateEngine;
//import org.thymeleaf.context.Context;


@Service
public class MailServiceImpl implements MailService {

    //验证邮件发送冷却时间限制，秒为单位
//    @Value("${spring.web.verify.mail-limit}")
//    int verifyLimit;
//
//    @Resource
//    AmqpTemplate rabbitTemplate;
//    @Resource
//    FlowUtils flow;


    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sendFrom;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @SneakyThrows
    @Override
    public void send(String subject, String email, String code) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true,"UTF-8");
        messageHelper.setFrom(sendFrom);
        messageHelper.setTo(email);
        messageHelper.setSubject(subject);
        messageHelper.setText(code,true);
        mailSender.send(message);
    }

    /**
     * 移除Redis中存储的邮件验证码
     * @param email 电邮
     */
    private void deleteEmailVerifyCode(String email){
        String key = Const.VERIFY_EMAIL_DATA + email;
        stringRedisTemplate.delete(key);
    }

    /**
     * 获取Redis中存储的邮件验证码
     * @param email 电邮
     * @return 验证码
     */
    private String getEmailVerifyCode(String email){
        String key = Const.VERIFY_EMAIL_DATA + email;
        return stringRedisTemplate.opsForValue().get(key);
    }
}
