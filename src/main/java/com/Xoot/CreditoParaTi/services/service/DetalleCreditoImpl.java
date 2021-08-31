package com.Xoot.CreditoParaTi.services.service;

import com.Xoot.CreditoParaTi.services.interfaces.IAnswerMedicalquestionnaireService;
import com.Xoot.CreditoParaTi.services.interfaces.IDetalleCredito;
import com.Xoot.CreditoParaTi.dto.*;
import com.Xoot.CreditoParaTi.entity.*;
import com.Xoot.CreditoParaTi.repositories.interfaces.*;
import com.Xoot.CreditoParaTi.utils.DocumentUtil;
import org.apache.commons.io.FileUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class DetalleCreditoImpl implements IDetalleCredito {
    @Autowired
    ICreditApplicationDao creditApplicationDao;
    @Autowired
    ICustomerDao customerDao;
    @Autowired
    IDocumentDao documentDao;
    @Autowired
    IAdditionalInformationDao additionalInformationDao;
    @Autowired
    IEconomicDependientiesDAO economicDependientiesDAO;
    @Autowired
    ISpouseDao spouseDao;
    @Autowired
    IWorkDao workDao;
    @Autowired
    IReferenceDao referenceDao;
    @Autowired
    IPropertyDao propertyDao;
    @Autowired
    IMedicalQuestionnaireDao medicalQuestionDao;
    @Autowired
    IAnswerMedicalquestionnaireService answerMedicalDao;
    @Autowired
    ICocreditedCustomersDao cocreditedCustomersDao;
    @Autowired
    ICocreditedAdditionalDao cocreditedAdditionalDao;
    @Autowired
    ICocreditedWorkDao cocreditedWorkDao;

    PdfDTO pdfDTO;
    DocumentUtil ObjDocUtil;

    @Autowired
    private ModelMapper modelMapper;

    private DetalleCredito detalleCredito;

    @Override
    public DetalleCredito findByCreditID(Integer creditID) {
        detalleCredito = new DetalleCredito();
        String base64File;
        Customer customer;
        Type lstTypeDocuments = new TypeToken<List<DocumentDTO>>() {
        }.getType();
        Type lstTypeEconomic = new TypeToken<List<EconomicDependientiesDto>>() {
        }.getType();
        Type lstTypeReference = new TypeToken<List<ReferenceDTO>>() {
        }.getType();

        List<DocumentDTO> listDocDTO = new ArrayList<DocumentDTO>();

        CreditApplication creditApplication = creditApplicationDao.FindByCreditUser(creditID);
        if (creditApplication != null) {
            if (creditApplication.getCreditId() != null) {
                customer = customerDao.findById(creditApplication.getCustomer()).orElse(null);
            } else {
                customer = customerDao.findByCreditId(creditID);
            }
        } else {
            customer = customerDao.findByCreditId(creditID);
        }
        List<Document> documents = documentDao.findByCreditId(creditID);
        List<Document> docSpouse = documentDao.findByCreditIdSpouse(creditID);
        AdditionalInformation additionalInformation = additionalInformationDao.findByCreditId(creditID);
        List<EconomicDependents> dependents = economicDependientiesDAO.findByCreditId(creditID);
        Spouse spouse = spouseDao.findByCreditId(creditID);
        Work work = workDao.findByCreditId(creditID);
        List<Reference> references = referenceDao.findByCreditId(creditID);
        Property property = propertyDao.findByCreditId(creditID);
        MedicalQuestionnaireAnswerDTO MedicalQuestion = answerMedicalDao.findByCreditID(creditID);
        CocreditedCustomers cocreditedCustomers = cocreditedCustomersDao.findByCreditId(creditID);
        CocreditedAdditional cocreditedAdditional = cocreditedAdditionalDao.findByCreditId(creditID);
        CocreditedWork cocreditedWork = cocreditedWorkDao.findByCreditId(creditID);

        Document Pdfexpediente = documentDao.findAllIds(creditID,11);
        Document Pdfsubcuenta = documentDao.findAllIds(creditID,12);

        if (customer != null) {
            detalleCredito.setCustomer(modelMapper.map(customer, CustomerDTO.class));
        }

        /*for (Integer idx = 0 ; idx < 12; idx++) {
            listDocDTO.add(idx, new DocumentDTO());
        }

        for(Document doc: documents) {
            listDocDTO.set(doc.getTypeDocumentId()-1,modelMapper.map(doc,DocumentDTO.class));
        }*/
        detalleCredito.setDocuments(modelMapper.map(documents,lstTypeDocuments));

        if (additionalInformation != null) {
            detalleCredito.setAdditionalies(modelMapper.map(additionalInformation, AdditionalInformationDTO.class));
        }

        if (dependents != null ) {
            detalleCredito.setDependents(modelMapper.map(dependents, lstTypeEconomic));
        }
        if (spouse != null) {
            SpouseDTO spouseDTO = modelMapper.map(spouse, SpouseDTO.class);
            spouseDTO.setImg1(new DocumentDTO());
            spouseDTO.setImg2(new DocumentDTO());
            spouseDTO.setImg3(new DocumentDTO());
            for (Document doc:docSpouse) {
                switch (doc.getTypeDocumentId()){
                     case 12:
                         spouseDTO.setImg1(modelMapper.map(doc,DocumentDTO.class));
                         break;
                     case 13:
                         spouseDTO.setImg2(modelMapper.map(doc,DocumentDTO.class));
                         break;
                     case 14:
                         spouseDTO.setImg3(modelMapper.map(doc,DocumentDTO.class));
                         break;
                 }
            }
            detalleCredito.setSpouse(spouseDTO);
        }
        if(work != null ) {
            detalleCredito.setWork(modelMapper.map(work, WorkDTO.class));
        }
        if (references != null) {
            detalleCredito.setReferences(modelMapper.map(references, lstTypeReference));
        }

        if (property != null) {
            detalleCredito.setProperty(modelMapper.map(property,PropertyDTO.class));
        }

        if (MedicalQuestion != null) {
            detalleCredito.setMedicalquestionnaire(MedicalQuestion);
        }

        if (cocreditedCustomers != null) {
            detalleCredito.setCocreditedCustomers(modelMapper.map(cocreditedCustomers,CocreditedCustomersDTO.class));
        }

        if (cocreditedAdditional != null) {
            detalleCredito.setCocreditedAdditional(modelMapper.map(cocreditedAdditional,CocreditedAdditionalDTO.class));
        }

        if (cocreditedWork != null) {
            detalleCredito.setCocreditedWork(modelMapper.map(cocreditedWork,CocreditedWorkDTO.class));
        }


        pdfDTO = new PdfDTO();
        ObjDocUtil = new DocumentUtil();

        if (Pdfexpediente != null) {
            pdfDTO.setExpediente(true);
        }

        if (Pdfsubcuenta != null) {
            pdfDTO.setSubcuenta(true);
        }

        detalleCredito.setPdf(pdfDTO);
        return detalleCredito;
    }
}
