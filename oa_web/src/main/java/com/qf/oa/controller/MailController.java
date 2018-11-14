package com.qf.oa.controller;

import com.qf.oa.entity.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author cx
 * @Time 2018/11/7 15:42
 * @Version 1.0
 */
@Controller
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private JavaMailSender javaMailSender;

    @RequestMapping("/tomail")
    public String toMail(){
        return "mail";
    }

    @RequestMapping("/sendmail")
    public String sendMail(Mail mail) throws MessagingException {
        //创建邮件
        MimeMessage message = javaMailSender.createMimeMessage();
        //初始化邮件辅助对象
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(message,true);
        //设置发件人
        mimeMessageHelper.setFrom("verygoodwlk@sina.cn");
        //设置发送方
        mimeMessageHelper.setTo(mail.getTo());
        //设置邮件标题
        mimeMessageHelper.setSubject(mail.getTitle());
        //设置邮件内容
        mimeMessageHelper.setText(mail.getContent(),true);
        //发送发件
        mimeMessageHelper.addAttachment(mail.getFile().getOriginalFilename(),new InputStreamSource(){

            @Override
            public InputStream getInputStream() throws IOException {
                return mail.getFile().getInputStream();
            }
        });

        //发送邮件
        javaMailSender.send(message);
        return "redirect:/mail/tomail";
    }
}
