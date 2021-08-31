package com.Xoot.CreditoParaTi.services.interfaces;


import com.Xoot.CreditoParaTi.mapper.Mail;

import java.util.Map;

public interface IMailService {
    public void sendEmail(Mail mail);
    public void sendEmailTemplete(Mail mail, Map<String, Object> props, String template);
}
