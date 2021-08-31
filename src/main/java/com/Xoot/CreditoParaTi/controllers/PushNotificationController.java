package com.Xoot.CreditoParaTi.controllers;

import com.Xoot.CreditoParaTi.mapper.Mail;
import com.Xoot.CreditoParaTi.services.interfaces.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/notification")
public class PushNotificationController {
    @Autowired
    private IMailService mailService;

    @GetMapping("test")
    public void testEmail(){
        Mail mail = new Mail();

        mail.setMailFrom("envios@creditoparati.com.mx");
        mail.setMailTo("valrapa@gmail.com");
        mail.setMailSubject("Spring Boot - Email Example");
        mail.setMailContent("Learn How to send Email using Spring Boot!!!\n\nThanks\nwww.technicalkeeda.com");
        Map<String,Object> prop= new HashMap<String,Object>();
        prop.put("name","Pedro Alberto Valdes Rangel");
        prop.put("username","pvalran@creditoparati.com");
        prop.put("password","valran");
        prop.put("link","http://url");

        mailService.sendEmail(mail);
    }
}
