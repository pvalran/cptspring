package com.Xoot.CreditoParaTi.services.service;

import com.Xoot.CreditoParaTi.mapper.DocStatusMap;
import com.Xoot.CreditoParaTi.services.interfaces.IAnswerMedicalquestionnaireService;
import com.Xoot.CreditoParaTi.services.interfaces.IDetalleCredito;
import com.Xoot.CreditoParaTi.dto.*;
import com.Xoot.CreditoParaTi.entity.*;
import com.Xoot.CreditoParaTi.repositories.interfaces.*;
import com.Xoot.CreditoParaTi.utils.DocumentUtil;
import org.apache.commons.io.FileUtils;
import org.hibernate.transform.Transformers;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
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

    @PersistenceUnit
    private EntityManagerFactory emf;


    @Override
    public DetalleCredito findByCreditID(Integer creditID) {
        detalleCredito = new DetalleCredito();
        Boolean solicitud = false;
        String base64File;
        Customer customer;
        EntityManager em = emf.createEntityManager();

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
                if (creditApplication.getCustomer() != null) {
                    customer = customerDao.findById(creditApplication.getCustomer()).orElse(null);
                } else {
                    customer = null;
                }
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
        Document Pdfexpediente = documentDao.findAllIds(creditID,10);
        Document Pdfsubcuenta = documentDao.findAllIds(creditID,11);


        List<DocStatusMap> items = em.createNativeQuery("select dt.id as typeDocument, case when COALESCE(d.number_request,1) = 1 then 0 else 1 end as status from documents_type dt " +
                "left join documents d on dt.id = d.type_document_id and d.number_request = :creditID and d.status_flag = 1 " +
                "order by dt.id").setParameter("creditID",creditID)
                .unwrap(org.hibernate.query.NativeQuery.class)
                .setResultTransformer(Transformers.aliasToBean(DocStatusMap.class))
                .getResultList();




        if (customer != null) {
            detalleCredito.setCustomer(modelMapper.map(customer, CustomerDTO.class));
        }
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

        if ( customer != null &&
            additionalInformation != null &&
            work != null &&
            dependents.stream().count() > 0 &&
            references.stream().count() > 0
        ) {
            if (additionalInformation.getCivilState() == 1) {
                if (cocreditedAdditional != null && cocreditedWork != null && cocreditedCustomers != null) {
                    solicitud = true;
                }
            } else {
                if (spouse != null) {
                    solicitud = true;
                } else {
                    solicitud = false;
                }
            }
        }

        detalleCredito.setDocumentStatus(items);
        detalleCredito.setSolicitud(solicitud);
        return detalleCredito;
    }
}
