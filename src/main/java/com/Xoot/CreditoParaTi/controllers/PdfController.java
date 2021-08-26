package com.Xoot.CreditoParaTi.controllers;

import com.Xoot.CreditoParaTi.entity.AdditionalInformation;
import com.Xoot.CreditoParaTi.entity.CreditApplication;
import com.Xoot.CreditoParaTi.entity.Customer;
import com.Xoot.CreditoParaTi.entity.transaction;
import com.Xoot.CreditoParaTi.repositories.interfaces.IAdditionalInformationDao;
import com.Xoot.CreditoParaTi.repositories.interfaces.ICreditApplicationDao;
import com.Xoot.CreditoParaTi.repositories.interfaces.ICustomerDao;
import com.Xoot.CreditoParaTi.repositories.interfaces.ITransactionDao;
import com.Xoot.CreditoParaTi.services.service.PdfGenerator;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.lowagie.text.DocumentException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class PdfController {

    @Autowired
    private PdfGenerator pdfGenerator;

    @Autowired
    private ICreditApplicationDao creditApplicationDao;

    @Autowired
    private ICustomerDao customerDao;

    @Autowired
    private IAdditionalInformationDao additionalDao;



    @Autowired
    private ITransactionDao transactionDao;

    private String templateName = "templatePDF.html";

    private String fileName = "reaffle.pdf";

    @GetMapping("/pdf/identidad/{creditId}")
    public ResponseEntity<ByteArrayResource> rafflePDF(@PathVariable("creditId") Integer creditId,
            final HttpServletRequest request,
            final HttpServletResponse response) throws DocumentException {

        ByteArrayOutputStream byteArrayOutputStreamPDF = pdfGenerator.createPdf(
                templateName,
                request,
                response,creditId);
        ByteArrayResource inputStreamResourcePDF = new ByteArrayResource(byteArrayOutputStreamPDF.toByteArray());

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName).contentType(MediaType.APPLICATION_PDF)
                .contentLength(inputStreamResourcePDF.contentLength()).body(inputStreamResourcePDF);

    }

    @GetMapping("/pdf/identidadview/{creditId}")
    public String raffleView(@PathVariable("creditId") Integer creditId,
            final HttpServletRequest request,
            final HttpServletResponse response,
                             Model model) throws DocumentException {
        Gson ObjJson = new Gson();

        CreditApplication credit = creditApplicationDao.FindByCreditUser(creditId);

        String statusINE = "R";
        String statusSelfie = "R";
        String statusCurp = "R";

        model.addAttribute("creditId","N/A");
        model.addAttribute("datePrint", "N/A");
        model.addAttribute("dateEnrolment","N/A");
        model.addAttribute("mobile", "N/A");
        model.addAttribute("email", "N/A");
        model.addAttribute("inevalido", "N/A");
        model.addAttribute("mrz", "N/A");
        model.addAttribute("vigencia", "N/A");
        model.addAttribute("selfie", "N/A");
        model.addAttribute("curp", "N/A");
        model.addAttribute("identidad", "N/A");

        if (credit != null){
            DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            model.addAttribute("creditId",credit.getCreditId());
            model.addAttribute("datePrint", dtf2.format(LocalDateTime.now()));
            model.addAttribute("dateEnrolment",credit.getCrtd_on());
            if (credit.getCustomer() != null) {
                Customer customer = customerDao.findById(credit.getCustomer()).orElse(null);
                Object ObjSelfie = null;
                Object ObjCurp = null;
                if (customer != null) {
                    AdditionalInformation additional = additionalDao.findByCreditId(credit.getCreditId());
                    model.addAttribute("mobile", additional.getMobile());
                    model.addAttribute("email", customer.getEmail());
                    transaction TransIne = transactionDao.findValidate(credit.getCreditId(), 1);
                    transaction TransSelfie = transactionDao.findValidate(credit.getCreditId(), 2);
                    transaction TransCurp = transactionDao.findValidate(credit.getCreditId(), 3);

                    if ( (TransIne != null) && (TransSelfie != null) && (TransCurp != null) ) {
                        LinkedTreeMap JsonIne = ObjJson.fromJson(TransIne.getTransactionCode(), LinkedTreeMap.class);
                        LinkedTreeMap JsonSelfie = ObjJson.fromJson(TransSelfie.getTransactionCode(), LinkedTreeMap.class);
                        LinkedTreeMap JsonCurp = ObjJson.fromJson(TransCurp.getTransactionCode(), LinkedTreeMap.class);


                        if (JsonIne.containsKey("error")) {
                            model.addAttribute("inevalido", "NO");
                            model.addAttribute("mrz", JsonIne.get("mensaje"));
                            model.addAttribute("vigencia", JsonIne.get("mensaje"));
                            statusINE = "R";
                        } else {
                            model.addAttribute("inevalido", "SI");
                            model.addAttribute("mrz", "SI");
                            model.addAttribute("vigencia", JsonIne.get("vigencia"));
                            statusINE = "A";
                        }

                        if (JsonSelfie.get("estatus") == "ok") {
                            model.addAttribute("selfie", "SI");
                            statusSelfie = "A";
                        } else {
                            model.addAttribute("selfie", "NO");
                            statusSelfie = "R";
                        }

                        if (JsonCurp.get("estatus") == "ok") {
                            model.addAttribute("curp", "SI");
                            statusCurp = "A";
                        } else {
                            model.addAttribute("curp", "NO");
                            statusCurp = "R";
                        }

                        if ((statusINE == "R") || (statusSelfie == "R")) {
                            model.addAttribute("identidad", "RECHAZADA");
                        } else if ((statusINE == "A") || (statusSelfie == "A") || (statusCurp == "A")) {
                            model.addAttribute("identidad", "APROBADA");
                        } else {
                            model.addAttribute("identidad", "PENDIENTE");
                        }
                    }
                    return "templatePDF.html";
                } else {
                    return "templatePDF.html";
                }
            } else {
                return "templatePDF.html";
            }
        }

        return "templatePDF.html";
    }


}
