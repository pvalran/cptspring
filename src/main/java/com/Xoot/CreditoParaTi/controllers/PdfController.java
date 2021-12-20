package com.Xoot.CreditoParaTi.controllers;

import com.Xoot.CreditoParaTi.dto.*;
import com.Xoot.CreditoParaTi.entity.*;
import com.Xoot.CreditoParaTi.mapper.Mail;
import com.Xoot.CreditoParaTi.repositories.interfaces.*;
import com.Xoot.CreditoParaTi.services.interfaces.IDetalleCredito;
import com.Xoot.CreditoParaTi.services.interfaces.IMailService;
import com.Xoot.CreditoParaTi.services.interfaces.IUserService;
import com.Xoot.CreditoParaTi.services.service.PdfGenerator;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.lowagie.text.DocumentException;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.WebContext;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@CrossOrigin(origins = "*")
public class PdfController {

    @Autowired
    private PdfGenerator pdfGenerator;

    @Autowired
    private TemplateEngine templateEngine;

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

    @Autowired
    private ILocationStateDao stateDao;

    @Autowired
    private ITypeDependentDao typeDependentDao;

    @Autowired
    private IDetalleCredito detalleCredito;

    @Autowired
    private IMailService mailService;

    @Autowired
    private IUserService userService;


    private String templateName = "templatePDF.html";

    private String fileName = "reaffle.pdf";

    private final Path root = Paths.get("/srv/www/upload");

    @GetMapping("/identidad/{creditId}")
    public ResponseEntity<ByteArrayResource> rafflePDF(@PathVariable("creditId") Integer creditId,
                                                       final HttpServletRequest request,
                                                       final HttpServletResponse response) throws DocumentException {

        ByteArrayOutputStream byteArrayOutputStreamPDF = pdfGenerator.createPdf(templateName, request, response, creditId);
        ByteArrayResource inputStreamResourcePDF = new ByteArrayResource(byteArrayOutputStreamPDF.toByteArray());

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName).contentType(MediaType.APPLICATION_PDF)
                .contentLength(inputStreamResourcePDF.contentLength()).body(inputStreamResourcePDF);

    }
    @GetMapping("/identidadview/{creditId}")
    public String raffleView(@PathVariable("creditId") Integer creditId,
                             final HttpServletRequest request,
                             final HttpServletResponse response,
                             Model model) throws DocumentException {
        Gson ObjJson = new Gson();

        CreditApplication credit = creditApplicationDao.FindByCreditUser(creditId);

        String statusINE = "R";
        String statusSelfie = "R";
        String statusCurp = "R";

        model.addAttribute("creditId", "N/A");
        model.addAttribute("datePrint", "N/A");
        model.addAttribute("dateEnrolment", "N/A");
        model.addAttribute("mobile", "N/A");
        model.addAttribute("email", "N/A");
        model.addAttribute("inevalido", "N/A");
        model.addAttribute("mrz", "N/A");
        model.addAttribute("vigencia", "N/A");
        model.addAttribute("selfie", "N/A");
        model.addAttribute("curp", "N/A");
        model.addAttribute("identidad", "N/A");

        if (credit != null) {
            DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            model.addAttribute("creditId", credit.getCreditId());
            model.addAttribute("datePrint", dtf2.format(LocalDateTime.now()));
            model.addAttribute("dateEnrolment", credit.getCrtd_on());
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

                    if ((TransIne != null) && (TransSelfie != null) && (TransCurp != null)) {
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

    @GetMapping("/error")
    public String errorPDf() {
        return "errorPDF";
    }

    @GetMapping("/apk")
    public String ViewApk() {
        return "downloadapk.html";
    }

    @GetMapping("/identidadreportPDF/{creditId}")
    public ResponseEntity<?> getPDF(@PathVariable("creditId") Integer creditId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        byte[] pdfResponse = pdfGenerator.createPdfItext(request, response, templateName, creditId);
        if (pdfResponse == null) {
            /*return ResponseEntity.status(HttpStatus.OK)
                    .location(URI.create("http://www.yahoo.com"))
                    .build();*/

            URI yahoo = URI.create("/pdf/error");
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(yahoo);
            return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
        }
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfResponse);
    }

    @GetMapping("/pdf/solicitud/{creditId}")
    public ResponseEntity<?> getPDFSolicitud(
        @PathVariable("creditId") Integer creditId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject resp = new JSONObject();
        HashMap<Integer, String> countryOfBirth = new HashMap<Integer, String>();
        HashMap<Integer, String> countryOfResidence = new HashMap<Integer, String>();
        HashMap<Integer, String> direccion = new HashMap<Integer, String>();
        HashMap<Integer, String> direccionWork = new HashMap<Integer, String>();

        HashMap<Integer, String> direccionCoacreditado = new HashMap<Integer, String>();
        HashMap<Integer, String> direccionWorkCoacreditado = new HashMap<Integer, String>();

        try {
            CreditApplication userCredit = creditApplicationDao.FindByCreditUser(creditId);


            if (userCredit == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("El número de credito no existe");
            }

            if (userCredit.getUser() == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("El número de credito no existe");
            }

            Usuario user = userService.findById(userCredit.getUser());
            if (user != null) {
                DetalleCredito creditObj = detalleCredito.findByCreditID(creditId);
                if (creditObj != null) {
                    String processedHtml = this.getViewSolicitud(creditId, request, response);
                    ByteArrayOutputStream target = new ByteArrayOutputStream();
                    ConverterProperties converterProperties = new ConverterProperties();
                    converterProperties.setBaseUri("http://localhost:8080");
                    /*ConverterProperties props = new ConverterProperties();
                    props.setMediaDeviceDescription(new MediaDeviceDescription(MediaType.PRINT));*/
                    HtmlConverter.convertToPdf(processedHtml, target, converterProperties);
                    byte[] bytes = target.toByteArray();
                    if (bytes.equals(null)) {
                        resp.put("data", "");
                        resp.put("message", "Correo no enviado, Error al generar el archivo de la solicitud");
                        resp.put("result", false);
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(resp.toString());
                    }

                    Path pathFile = this.root.resolve("solicitud_" + creditId + ".pdf");
                    if (Files.exists(pathFile)) {
                        File filename = pathFile.toFile();
                        filename.delete();
                    }
                    File filename = pathFile.toFile();
                    FileUtils.writeByteArrayToFile(filename, bytes);
                    Mail mail = new Mail();
                    mail.setMailFrom("envios@creditoparati.com.mx");
                    mail.setMailTo(user.getEmail());
                    mail.setMailSubject("Credito para Ti - Solicitud de Credito para Ti");
                    mail.setMailContent("");
                    Map<String, Object> prop = new HashMap<String, Object>();



                    String customerName = "";
                    String customerPaternal = "";
                    String customerMaternal = "";
                    if (creditObj.getCustomer() != null ) {
                        customerName = (creditObj.getCustomer().getName() == null)? "" : creditObj.getCustomer().getName();
                        customerPaternal =  (creditObj.getCustomer().getPaternalLastName() == null)? "" : creditObj.getCustomer().getPaternalLastName();
                        customerMaternal =  (creditObj.getCustomer().getMotherLastName() == null)? "" : creditObj.getCustomer().getMotherLastName();
                    }

                    prop.put("name", customerName +" " +customerPaternal + " " + customerMaternal);
                    HashMap<String, byte[]> file = new HashMap<String, byte[]>();
                    file.put("solicitud_" + creditId + ".pdf", bytes);
                    mailService.sendEmailTemplete(mail, prop, "emailSolicitud", file);
                    resp.put("data", "");
                    resp.put("message", "Correo enviado");
                    resp.put("result", true);
                    return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(resp.toString());
                } else {
                    resp.put("data", "");
                    resp.put("message", "Correo no enviado, El número de credito no existe");
                    resp.put("result", false);
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(resp.toString());
                }
            } else {
                resp.put("data", "");
                resp.put("message", "Correo no enviado, el usuario no existe");
                resp.put("result", false);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(resp.toString());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            resp.put("data", "");
            resp.put("message", ex.getMessage());
            resp.put("result", false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(resp.toString());
        }
    }

    @GetMapping("/pdf/solicitud/view/{creditId}")
    public ResponseEntity<?> getPDFViewSolicitud(
            @PathVariable("creditId") Integer creditId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject resp = new JSONObject();
        try {

            String processedHtml = this.getViewSolicitud(creditId, request, response);
            return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(processedHtml);
        } catch (Exception ex) {
            resp.put("data", "");
            resp.put("message", ex.getMessage());
            resp.put("result", false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(resp.toString());
        }
    }

    private String getViewSolicitud(Integer creditId, HttpServletRequest request, HttpServletResponse response) {
        JSONObject resp = new JSONObject();
        HashMap<Integer, String> countryOfBirth = new HashMap<Integer, String>();
        HashMap<Integer, String> countryOfResidence = new HashMap<Integer, String>();
        HashMap<Integer, String> direccion = new HashMap<Integer, String>();
        HashMap<Integer, String> direccionWork = new HashMap<Integer, String>();

        HashMap<Integer, String> direccionCoacreditado = new HashMap<Integer, String>();
        HashMap<Integer, String> direccionWorkCoacreditado = new HashMap<Integer, String>();

        try {
            CreditApplication userCredit = creditApplicationDao.FindByCreditUser(creditId);
            countryOfBirth.put(0,"México");
            countryOfBirth.put(1,"Otro");

            countryOfResidence.put(0,"México");
            countryOfResidence.put(1,"Otro");

            direccion.put(0,"");
            direccion.put(1,"");
            direccion.put(2,"");
            direccion.put(3,"");
            direccion.put(4,"");
            direccion.put(5,"");
            direccion.put(6,"");

            direccionWork.put(0,"");
            direccionWork.put(1,"");
            direccionWork.put(2,"");
            direccionWork.put(3,"");
            direccionWork.put(4,"");
            direccionWork.put(5,"");
            direccionWork.put(6,"");

            direccionCoacreditado.put(0,"");
            direccionCoacreditado.put(1,"");
            direccionCoacreditado.put(2,"");
            direccionCoacreditado.put(3,"");
            direccionCoacreditado.put(4,"");
            direccionCoacreditado.put(5,"");
            direccionCoacreditado.put(6,"");

            direccionWorkCoacreditado.put(0,"");
            direccionWorkCoacreditado.put(1,"");
            direccionWorkCoacreditado.put(2,"");
            direccionWorkCoacreditado.put(3,"");
            direccionWorkCoacreditado.put(4,"");
            direccionWorkCoacreditado.put(5,"");
            direccionWorkCoacreditado.put(6,"");



            Usuario user = userService.findById(userCredit.getUser());
            if (user != null) {
                WebContext context = new WebContext(request, response, servletContext);
                List<EconomicDependientiesDto> lstEconomic = new ArrayList<EconomicDependientiesDto>();
                List<ReferenceDTO> lstReference = new ArrayList<ReferenceDTO>();
                List<FreeQuestionnaireDTO> lstFreeAnswer = new ArrayList<FreeQuestionnaireDTO>();
                List<LocationState> lstState;
                HashMap<String, Object> medicquestionnario = new HashMap<String, Object>();
                HashMap<Integer, Object> typeDependent = new HashMap<Integer, Object>();
                HashMap<Integer, Object> typeOccupation = new HashMap<Integer, Object>();
                HashMap<Integer, Object> typeStates = new HashMap<Integer, Object>();
                HashMap<Integer, Object> typePosition = new HashMap<Integer, Object>();
                HashMap<Integer, Object> typeActivity = new HashMap<Integer, Object>();


                medicquestionnario.put("weight", -1);
                medicquestionnario.put("height", -1);
                medicquestionnario.put("answer1", null);
                medicquestionnario.put("answer2", null);
                medicquestionnario.put("answer3", null);
                medicquestionnario.put("answer4", null);
                medicquestionnario.put("answer5", null);
                medicquestionnario.put("answer6", null);
                medicquestionnario.put("answer7", null);
                medicquestionnario.put("answer8", null);
                medicquestionnario.put("answer9", null);
                medicquestionnario.put("answer10", null);
                medicquestionnario.put("answer11", null);
                medicquestionnario.put("answer12", null);
                medicquestionnario.put("answer13", 0);


                List<TypeDependent> typeDependents = typeDependentDao.findAllActive();
                typeDependent.put(0,"");
                for (TypeDependent dependent:typeDependents)
                {
                    typeDependent.put(dependent.getIdTypeDependent(),dependent.getDescription());
                }

                typeOccupation.put(0, "");
                typeOccupation.put(1, "Ama(o) de casa");
                typeOccupation.put(2, "Empleado(a)");
                typeOccupation.put(3, "Estudiante");


                typePosition.put(1, "Empleado");
                typePosition.put(2, "Funcionario");
                typePosition.put(3, "Directivo");
                typePosition.put(4, "Socio, dueño, propietario");
                typePosition.put(5, "Profesionista independiente");
                typePosition.put(6, "Pensionado");
                typePosition.put(7, "Jubilado");
                typePosition.put(8, "Otro");

                typeActivity.put(1, "Comercio");
                typeActivity.put(2, "Industria");
                typeActivity.put(3, "Servicios");
                typeActivity.put(4, "Agropecuario");
                typeActivity.put(5, "Construccion");
                typeActivity.put(6, "Otro");


                lstState = stateDao.findAll();
                typeStates.put(0,"");
                for (LocationState state : lstState) {
                    typeStates.put(state.getIdState(), state.getName());
                }

                CustomerDTO customer = new CustomerDTO();
                AdditionalInformationDTO additional = new AdditionalInformationDTO();
                SpouseDTO spouse = new SpouseDTO();
                WorkDTO work = new WorkDTO();
                CocreditedCustomersDTO cocreditedCustomers = new CocreditedCustomersDTO();
                CocreditedAdditionalDTO cocreditedAdditional = new CocreditedAdditionalDTO();
                CocreditedWorkDTO cocreditedWork = new CocreditedWorkDTO();
                PropertyDTO property = new PropertyDTO();

                context.setVariable("typeDependent", typeDependent);
                context.setVariable("typeOccupation", typeOccupation);
                context.setVariable("typeStates", typeStates);
                context.setVariable("typePosition", typePosition);
                context.setVariable("typeActivity", typeActivity);
                context.setVariable("countryOfBirth", countryOfBirth);
                context.setVariable("countryOfResidence", countryOfResidence);
                context.setVariable("product", "Fovisste para todos");

                DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                context.setVariable("dateRequest", dtf2.format(LocalDateTime.now()));
                context.setVariable("numberRequest", creditId);
                DetalleCredito creditID = detalleCredito.findByCreditID(creditId);

                customer.setState_id(-1);
                customer.setStateOfBirth_id(-1);

                additional.setState("-1");
                additional.setScholarship(-1);
                additional.setMaritalStatus(-1);
                additional.setCountryOfBirth(-1);
                additional.setCountryOfResidence(-1);
                additional.setCountry("-1");

                work.setState("-1");
                work.setPosition(-1);
                work.setTypeContract(-1);
                work.setLaboral_activity(-1);

                cocreditedWork.setState("-1");
                cocreditedWork.setPosition(-1);
                cocreditedWork.setTypeContract(-1);
                cocreditedWork.setLaboralActivity(-1);

                cocreditedAdditional.setCountryBirth(0);
                cocreditedAdditional.setCountryResidence(0);

                cocreditedCustomers.setState("-1");
                cocreditedCustomers.setStateBirth(-1);



                if (creditID != null) {

                    if (creditID.getCustomer() == null){
                        creditID.setCustomer(customer);
                    } else {
                        String[] strDireccion = creditID.getCustomer().getStreetAndNumber().split(",");
                        for (Integer idx=0; idx<strDireccion.length;idx++){
                            if (strDireccion[idx].contains("-")) {
                                direccion.put(idx, strDireccion[idx].split("-")[1]);
                            } else {
                                direccion.put(idx, strDireccion[idx]);
                            }

                        }
                    }

                    if(creditID.getAdditionalies() == null) {
                        creditID.setAdditionalies(additional);
                    }


                    context.setVariable("direccion",direccion);
                    context.setVariable("creditId", creditID);
                    if (creditID.getSpouse() != null) {
                        spouse = creditID.getSpouse();
                    }
                    context.setVariable("spouse", spouse);

                    if (creditID.getWork() != null) {
                        work = creditID.getWork();
                        work.setAddress(creditID.getWork().getAddress().split(",")[0]);

                        if (creditID.getWork().getSuburb().contains("-")) {
                            work.setSuburb(creditID.getWork().getSuburb().split("-")[1]);
                        }

                        if (creditID.getWork().getCity().contains("-")) {
                            work.setCity(creditID.getWork().getCity().split("-")[1]);
                        }

                        if (creditID.getWork().getMunicipality().contains("-")) {
                            work.setMunicipality(creditID.getWork().getMunicipality().split("-")[1]);
                        }
                    }

                    context.setVariable("work", work);
                    context.setVariable( "StateWork",Integer.parseInt(work.getState()));
                    if (creditID.getCocreditedCustomers() != null) {
                        cocreditedCustomers = creditID.getCocreditedCustomers();

                        cocreditedCustomers.setAddressNumber(creditID.getCocreditedCustomers().getAddressNumber().split(",")[0]);

                        if (creditID.getCocreditedCustomers().getSuburb().contains("-")) {
                            cocreditedCustomers.setSuburb(creditID.getCocreditedCustomers().getSuburb().split("-")[1]);
                        }

                        if (creditID.getCocreditedCustomers().getCity().contains("-")) {
                            cocreditedCustomers.setCity(creditID.getCocreditedCustomers().getCity().split("-")[1]);
                        }

                        if (creditID.getCocreditedCustomers().getCounty().contains("-")) {
                            cocreditedCustomers.setCounty(creditID.getCocreditedCustomers().getCounty().split("-")[1]);
                        }
                    }

                    context.setVariable("cocreditedCustomers", cocreditedCustomers);
                    context.setVariable("StateCoCustomers",Integer.parseInt(cocreditedCustomers.getState()));
                    if (creditID.getCocreditedAdditional() != null) {
                        cocreditedAdditional = creditID.getCocreditedAdditional();

                        cocreditedAdditional.setAddress(creditID.getCocreditedAdditional().getAddress().split(",")[0]);

                        if (creditID.getCocreditedAdditional().getSuburb().contains("-")) {
                            cocreditedAdditional.setSuburb(creditID.getCocreditedAdditional().getSuburb().split("-")[1]);
                        }

                        if (creditID.getCocreditedAdditional().getCity().contains("-")) {
                            cocreditedAdditional.setCity(creditID.getCocreditedAdditional().getCity().split("-")[1]);
                        }

                        if (creditID.getCocreditedAdditional().getCounty().contains("-")) {
                            cocreditedAdditional.setCounty(creditID.getCocreditedAdditional().getCounty().split("-")[1]);
                        }


                    }
                    context.setVariable("cocreditedAdditional", cocreditedAdditional);

                    if (creditID.getCocreditedWork() != null) {
                        cocreditedWork = creditID.getCocreditedWork();
                        cocreditedWork.setAddress(creditID.getCocreditedWork().getAddress().split(",")[0]);

                        if (creditID.getCocreditedWork().getSuburb().contains("-")) {
                            cocreditedWork.setSuburb(creditID.getCocreditedWork().getSuburb().split("-")[1]);
                        }

                        if (creditID.getCocreditedWork().getCity().contains("-")) {
                            cocreditedWork.setCity(creditID.getCocreditedWork().getCity().split("-")[1]);
                        }

                        if (creditID.getCocreditedWork().getCounty().contains("-")) {
                            cocreditedWork.setCounty(creditID.getCocreditedWork().getCounty().split("-")[1]);
                        }
                    }
                    context.setVariable("cocreditedWork", cocreditedWork);
                    context.setVariable( "StateCoWork",Integer.parseInt(cocreditedWork.getState()));
                    if (creditID.getDependents() != null) {
                        for (EconomicDependientiesDto economic : creditID.getDependents()) {
                            lstEconomic.add(economic);
                        }
                    }
                    lstReference.add(new ReferenceDTO());
                    lstReference.add(new ReferenceDTO());
                    lstReference.add(new ReferenceDTO());
                    lstReference.add(new ReferenceDTO());
                    Integer idxRef = 0;
                    if (creditID.getReferences() != null) {
                        for (ReferenceDTO reference : creditID.getReferences()) {
                            lstReference.set(idxRef, reference);
                            idxRef++;
                        }
                    }
                    for (Integer idx = lstEconomic.size(); idx <= 4; idx++) {
                        EconomicDependientiesDto economicDependient = new EconomicDependientiesDto();
                        economicDependient.setTypeDependent(-1);
                        economicDependient.setTypeOccupation(-1);
                        lstEconomic.add(economicDependient);
                    }
                    context.setVariable("economics", lstEconomic);
                    context.setVariable("references", lstReference);
                    context.setVariable("lstFreeAnswer", lstFreeAnswer);
                    if (creditID.getMedicalquestionnaire() != null) {
                        MedicalQuestionnaireAnswerDTO medical = creditID.getMedicalquestionnaire();
                        medicquestionnario.put("weight", medical.getWeight());
                        medicquestionnario.put("height", medical.getHeight());
                        for (AnswerQuestionnaireDTO answer : medical.getanswerQuestionnairies()) {
                            String key = "answer" + answer.getAnswerNumer();
                            medicquestionnario.put(key, answer.getAnswer());
                        }
                        context.setVariable("lstFreeAnswer", medical.getFreeQuestionnairies());
                    }

                    Double weight = Double.parseDouble(medicquestionnario.get("weight").toString());
                    Double height = Double.parseDouble(medicquestionnario.get("height").toString());


                    if ( weight > 0){
                        medicquestionnario.put("weight",weight+40);
                    } else {
                        medicquestionnario.put("weight",0);
                    }

                    if ( height > 0){
                        medicquestionnario.put("height",height+60);
                    } else {
                        medicquestionnario.put("height",0);
                    }

                    context.setVariable("medicquestionnario", medicquestionnario);

                    if (creditID.getProperty() != null){
                        property = creditID.getProperty();
                    }

                    Integer propertyState = -1;
                    if (property.getState() != null){
                        propertyState = Integer.parseInt(property.getState().trim())+1;
                    }

                    context.setVariable("property",property);
                    context.setVariable("propertyState",propertyState);

                    String Name = (creditID.getCustomer().getName() == null) ? "" : creditID.getCustomer().getName();
                    String lastName = (creditID.getCustomer().getPaternalLastName() == null) ? "" : creditID.getCustomer().getPaternalLastName() ;
                    String secondLastName = (creditID.getCustomer().getMotherLastName() == null) ? "" : creditID.getCustomer().getMotherLastName() ;
                    String firmaSolicitante = Name + " " + lastName + " " + secondLastName;

                    String firmaCoyunge = "";
                    if (spouse == null) {
                        firmaCoyunge = "";
                    } else {
                        String SpouseName = (spouse.getName() == null) ? "" : spouse.getName();
                        String SpouseLastName = (spouse.getPaternalLastName() == null) ? "" : spouse.getPaternalLastName();
                        String SpouseSecondLastName = (spouse.getMaternalLastName() == null) ? "" : spouse.getMaternalLastName();
                        firmaCoyunge = SpouseName + " " + SpouseLastName + " " + SpouseSecondLastName;
                    }

                    String firmaCoacreditado = "";
                    if(cocreditedCustomers == null) {
                        firmaCoacreditado = "";
                    } else {
                        String CoacreditadoName = (cocreditedCustomers.getName() == null) ? "" : cocreditedCustomers.getName();
                        String CoacreditadoLastName = (cocreditedCustomers.getPaternalLastName() == null) ? "" : cocreditedCustomers.getPaternalLastName();
                        String CoacreditadoSecondLastName = (cocreditedCustomers.getMotherLastName() == null) ? "" : cocreditedCustomers.getMotherLastName();
                        firmaCoacreditado = CoacreditadoName + " " + CoacreditadoLastName + " " + CoacreditadoSecondLastName;
                    }
                    String firmaObligado = "";
                    Integer coacreditado = 1;
                    if (creditID.getAdditionalies().getCivilState() != null){
                        if (creditID.getAdditionalies().getCivilState() == 1) {
                            if (creditID.getAdditionalies().getIncomeSpouse().equals("false")) {
                                firmaObligado = firmaCoyunge;
                                coacreditado = 0;
                                firmaCoacreditado = "";
                            }
                        }
                    }

                    context.setVariable("coacreditado", coacreditado);
                    context.setVariable("firmaSolicitante", firmaSolicitante);
                    context.setVariable("firmaCoyunge", firmaCoyunge);
                    context.setVariable("firmaCoacreditado", firmaCoacreditado);
                    context.setVariable("firmaObligado", firmaObligado);

                    String processedHtml = templateEngine.process("solicitud", context);
                    return processedHtml;
                } else {
                    resp.put("data", "");
                    resp.put("message", "Correo no enviado, El número de credito no existe");
                    resp.put("result", false);
                    return resp.toString();
                }
            } else {
                resp.put("data", "");
                resp.put("message", "Correo no enviado, el usuario no existe");
                resp.put("result", false);
                return resp.toString();
            }
        } catch (Exception ex) {
            resp.put("data", "");
            resp.put("message", ex.getMessage());
            resp.put("result", false);
            return resp.toString();
        }
    }

    @GetMapping("/pdf/identidadreport/{creditId}")
    public ResponseEntity<?> getPDFView(@PathVariable("creditId") Integer creditId,
            HttpServletRequest request, HttpServletResponse response) throws IOException {
        WebContext context = new WebContext(request, response, servletContext);
        Gson ObjJson = new Gson();
        CreditApplication credit = creditApplicationDao.FindByCreditUser(creditId);

        String statusINE = "R";
        String statusSelfie = "R";
        String statusCurp = "R";


        if (credit != null) {
            DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            context.setVariable("creditId", credit.getCreditId());
            context.setVariable("datePrint", dtf2.format(LocalDateTime.now()));
            context.setVariable("dateEnrolment", credit.getCrtd_on());
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

                        if (JsonIne.containsKey("error") || JsonIne.containsKey("ERROR")) {
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

                        if ((JsonSelfie.get("estatus").toString().toLowerCase() == "ok") || (JsonSelfie.get("estatus").toString().equals("OK"))) {
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
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body("Error en la descargar");
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .contentType(MediaType.APPLICATION_JSON)
                            .body("Error en la descargar");
                }
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("Error en la descargar");
            }
        }
        String processedHtml = templateEngine.process("templatePDF", context);
        ByteArrayOutputStream target = new ByteArrayOutputStream();
        ConverterProperties converterProperties = new ConverterProperties();
        converterProperties.setBaseUri("http://localhost:8080");
        HtmlConverter.convertToPdf(processedHtml, target, converterProperties);
        byte[] bytes = target.toByteArray();

        Path pathFile = this.root.resolve("reporteIdentidad_" + creditId + ".pdf");
        if (Files.exists(pathFile)) {
            File filename = pathFile.toFile();
            filename.delete();
        }
        File filename = pathFile.toFile();
        FileUtils.writeByteArrayToFile(filename, bytes);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(filename));

        HttpHeaders cabecera = new HttpHeaders();
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reporteIdentidad_" + creditId + ".pdf");
        return new ResponseEntity<Resource>(resource, cabecera, HttpStatus.OK);
    }
}
