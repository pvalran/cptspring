package com.Xoot.CreditoParaTi.services.service;

import com.Xoot.CreditoParaTi.mapper.Mail;
import com.Xoot.CreditoParaTi.services.interfaces.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.UnsupportedEncodingException;

import java.util.*;

@Service()
public class MailImpl implements IMailService {

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;



    @Override
    public void sendEmail(Mail mail) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setSubject(mail.getMailSubject());
            mimeMessageHelper.setFrom(new InternetAddress(mail.getMailFrom(), "creditoparati.com"));
            mimeMessageHelper.setTo(mail.getMailTo());
            mimeMessageHelper.setText(mail.getMailContent());
            mailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void sendEmailTemplete(Mail mail, Map<String, Object> props,String template){
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessageHelper.setSubject(mail.getMailSubject());
            mimeMessageHelper.setFrom(new InternetAddress(mail.getMailFrom(), "creditoparati.com"));
            mimeMessageHelper.setTo(mail.getMailTo());
            mimeMessageHelper.setEncodeFilenames(true);
            Context context = new Context();
            context.setVariables(props);
            String html = templateEngine.process(template, context);
            mimeMessageHelper.setText(html,true);
            mailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void sendEmailTemplete(Mail mail, Map<String, Object> props,String template,HashMap<String, byte[]> files){
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessageHelper.setSubject(mail.getMailSubject());
            mimeMessageHelper.setFrom(new InternetAddress(mail.getMailFrom(), "creditoparati.com"));
            mimeMessageHelper.setTo(mail.getMailTo());
            mimeMessageHelper.setEncodeFilenames(true);
            Context context = new Context();
            context.setVariables(props);
            String html = templateEngine.process(template, context);
            mimeMessageHelper.setText(html,true);
            for (Map.Entry<String, byte[]> entry : files.entrySet()) {
                mimeMessageHelper.addAttachment(entry.getKey(), new ByteArrayResource(entry.getValue()));
            }


            mailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
