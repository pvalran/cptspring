package com.Xoot.CreditoParaTi.services.service;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Xoot.CreditoParaTi.entity.AdditionalInformation;
import com.Xoot.CreditoParaTi.entity.CreditApplication;
import com.Xoot.CreditoParaTi.entity.Customer;
import com.Xoot.CreditoParaTi.entity.transaction;
import com.Xoot.CreditoParaTi.repositories.interfaces.IAdditionalInformationDao;
import com.Xoot.CreditoParaTi.repositories.interfaces.ICreditApplicationDao;
import com.Xoot.CreditoParaTi.repositories.interfaces.ICustomerDao;
import com.Xoot.CreditoParaTi.repositories.interfaces.ITransactionDao;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.itextpdf.html2pdf.ConverterProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.WebContext;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;

import java.io.*;
import java.util.Objects;

import com.itextpdf.html2pdf.HtmlConverter;

@Service
public class PdfGenerator {

    private static final Logger logger = LoggerFactory.getLogger(PdfGenerator.class);

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private ApplicationContext context;

    @Autowired
    ServletContext servletContext;


    @Autowired
    private ICreditApplicationDao creditApplicationDao;

    @Autowired
    private ICustomerDao customerDao;

    @Autowired
    private IAdditionalInformationDao additionalDao;

    @Autowired
    private ITransactionDao transactionDao;

    String urlBase = "https://pimaid.dev:8443/Cpttablero";

    public ByteArrayOutputStream createPdf(final String templateName, final HttpServletRequest request, final HttpServletResponse response,Integer creditId)
            throws DocumentException {
        logger.debug("Generando informe pdf");
        Assert.notNull(templateName, "The templateName can not be null");
        Context context = new Context();
        Gson ObjJson = new Gson();
        CreditApplication credit = creditApplicationDao.FindByCreditUser(creditId);
        String statusINE = "R";
        String statusSelfie = "R";
        String statusCurp = "R";
        if (credit != null){
            DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            context.setVariable("creditId",credit.getCreditId());
            context.setVariable("datePrint", dtf2.format(LocalDateTime.now()));
            context.setVariable("dateEnrolment",credit.getCrtd_on());
            if (credit.getCustomer() != null) {
                Customer customer = customerDao.findById(credit.getCustomer()).orElse(null);
                Object ObjSelfie = null;
                Object ObjCurp = null;
                if (customer != null) {
                    AdditionalInformation additional = additionalDao.findByCreditId(credit.getCreditId());
                    if (additional != null) {
                        context.setVariable("mobile", additional.getMobile());
                    } else {
                        context.setVariable("mobile", "");
                    }
                    context.setVariable("email", customer.getEmail());
                    transaction TransIne = transactionDao.findValidate(credit.getCreditId(), 1);
                    transaction TransSelfie = transactionDao.findValidate(credit.getCreditId(), 2);
                    transaction TransCurp = transactionDao.findValidate(credit.getCreditId(), 3);
                    LinkedTreeMap JsonIne = ObjJson.fromJson(TransIne.getTransactionCode(), LinkedTreeMap.class);
                    LinkedTreeMap JsonSelfie = ObjJson.fromJson(TransSelfie.getTransactionCode(), LinkedTreeMap.class);
                    LinkedTreeMap JsonCurp = ObjJson.fromJson(TransCurp.getTransactionCode(), LinkedTreeMap.class);
                    if (JsonIne.containsKey("error")) {
                        context.setVariable("inevalido", "NO");
                        context.setVariable("mrz", JsonIne.get("mensaje"));
                        context.setVariable("vigencia", JsonIne.get("mensaje"));
                        statusINE = "R";
                    } else {
                        context.setVariable("inevalido", "SI");
                        context.setVariable("mrz", "SI");
                        context.setVariable("vigencia", JsonIne.get("vigencia"));
                        statusINE = "A";
                    }
                    if (JsonSelfie.get("estatus") == "ok") {
                        context.setVariable("selfie", "SI");
                        statusSelfie = "A";
                    } else {
                        context.setVariable("selfie", "NO");
                        statusSelfie = "R";
                    }
                    if (JsonCurp.get("estatus") == "ok") {
                        context.setVariable("curp", "SI");
                        statusCurp = "A";
                    } else {
                        context.setVariable("curp", "NO");
                        statusCurp = "R";
                    }
                    if ((statusINE == "R") || (statusSelfie == "R")) {
                        context.setVariable("identidad", "RECHAZADA");
                    } else if ((statusINE == "A") || (statusSelfie == "A") || (statusCurp == "A")) {
                        context.setVariable("identidad", "APROBADA");
                    } else {
                        context.setVariable("identidad", "PENDIENTE");
                    }
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }
        String processedHtml = templateEngine.process(templateName, context);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(processedHtml, urlBase);
            renderer.layout();
            renderer.createPDF(bos, false);
            renderer.finishPDF();
            logger.info("PDF created correctamente");
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    logger.error("Error creando pdf", e);
                }
            }
        }
        return bos;
    }

    public byte[] createPdfItext(HttpServletRequest request, HttpServletResponse response,final String templateName,Integer creditId) throws IOException {
        WebContext context = new WebContext(request, response, servletContext);
        Gson ObjJson = new Gson();
        CreditApplication credit = creditApplicationDao.FindByCreditUser(creditId);
        String statusINE = "R";
        String statusSelfie = "R";
        String statusCurp = "R";
        if (credit != null){
            DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            context.setVariable("creditId",credit.getCreditId());
            context.setVariable("datePrint", dtf2.format(LocalDateTime.now()));
            context.setVariable("dateEnrolment",credit.getCrtd_on());
            if (credit.getCustomer() != null) {
                Customer customer = customerDao.findById(credit.getCustomer()).orElse(null);
                Object ObjSelfie = null;
                Object ObjCurp = null;
                if (customer != null) {
                    AdditionalInformation additional = additionalDao.findByCreditId(credit.getCreditId());
                    if (additional != null) {
                        context.setVariable("mobile", additional.getMobile());
                    } else {
                        context.setVariable("mobile", " S/N");
                    }
                    context.setVariable("email", customer.getEmail());
                    transaction TransIne = transactionDao.findValidate(credit.getCreditId(), 1);
                    transaction TransSelfie = transactionDao.findValidate(credit.getCreditId(), 2);
                    transaction TransCurp = transactionDao.findValidate(credit.getCreditId(), 3);

                    if (TransIne != null && TransSelfie != null && TransCurp != null) {
                        LinkedTreeMap JsonIne = ObjJson.fromJson(TransIne.getTransactionCode(), LinkedTreeMap.class);
                        LinkedTreeMap JsonSelfie = ObjJson.fromJson(TransSelfie.getTransactionCode(), LinkedTreeMap.class);
                        LinkedTreeMap JsonCurp = ObjJson.fromJson(TransCurp.getTransactionCode(), LinkedTreeMap.class);

                        if (JsonIne.containsKey("error") || JsonIne.containsKey("ERROR") ) {
                            context.setVariable("inevalido", "NO");
                            context.setVariable("mrz", JsonIne.get("mensaje"));
                            context.setVariable("vigencia", JsonIne.get("mensaje"));
                            statusINE = "R";
                        } else {
                            context.setVariable("inevalido", "SI");
                            context.setVariable("mrz", "SI");
                            context.setVariable("vigencia", JsonIne.get("vigencia"));
                            statusINE = "A";
                        }

                        if ( (JsonSelfie.get("estatus").toString().toLowerCase().equals("ok")) || (JsonSelfie.get("estatus").toString().equals("OK") ) ) {
                            context.setVariable("selfie", "SI");
                            statusSelfie = "A";
                        } else {
                            context.setVariable("selfie", "NO");
                            statusSelfie = "R";
                        }

                        if (JsonCurp.get("estatus").toString().toLowerCase().equals("ok")) {
                            context.setVariable("curp", "SI");
                            statusCurp = "A";
                        } else {
                            context.setVariable("curp", "NO");
                            statusCurp = "R";
                        }

                        if ((statusINE == "R") || (statusSelfie == "R")) {
                            context.setVariable("identidad", "RECHAZADA");
                        } else if ((statusINE == "A") || (statusSelfie == "A") || (statusCurp == "A")) {
                            context.setVariable("identidad", "APROBADA");
                        } else {
                            context.setVariable("identidad", "PENDIENTE");
                        }
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }
        String processedHtml = templateEngine.process(templateName, context);
        ByteArrayOutputStream target = new ByteArrayOutputStream();
        ConverterProperties converterProperties = new ConverterProperties();
        converterProperties.setBaseUri("https://pimaid.dev:8443");
        HtmlConverter.convertToPdf(processedHtml, target, converterProperties);
        byte[] bytes = target.toByteArray();
        return bytes;
    }



}
