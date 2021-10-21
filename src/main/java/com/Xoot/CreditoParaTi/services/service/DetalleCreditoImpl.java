package com.Xoot.CreditoParaTi.services.service;

import com.Xoot.CreditoParaTi.services.interfaces.IAnswerMedicalquestionnaireService;
import com.Xoot.CreditoParaTi.services.interfaces.IDetalleCredito;
import com.Xoot.CreditoParaTi.dto.*;
import com.Xoot.CreditoParaTi.entity.*;
import com.Xoot.CreditoParaTi.repositories.interfaces.*;
import com.Xoot.CreditoParaTi.utils.DocumentUtil;
import org.hibernate.transform.Transformers;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class DetalleCreditoImpl implements IDetalleCredito {
    @PersistenceUnit
    private EntityManagerFactory emf;

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
    @Autowired
    IDocumentTypeDao documentTypeDao;


    PdfDTO pdfDTO;
    DocumentUtil ObjDocUtil;

    @Autowired
    private ModelMapper modelMapper;

    private DetalleCredito detalleCredito;

    private final Logger log = LoggerFactory.getLogger(DetalleCreditoImpl.class);

    @Override
    public DetalleCredito findByCreditID(Integer creditID) {
        detalleCredito = new DetalleCredito();
        Boolean solicitud = false;
        String base64File;
        Customer customer;
        List<DocStatusMap> items = new ArrayList<DocStatusMap>();
        Integer sizeDepedents = 0;
        Integer sizeRerefeces = 0;
        //EntityManager em = emf.createEntityManager();

        Type lstTypeDocuments = new TypeToken<List<DocumentDTO>>() {}.getType();
        Type lstTypeEconomic = new TypeToken<List<EconomicDependientiesDto>>() {}.getType();
        Type lstTypeReference = new TypeToken<List<ReferenceDTO>>() {}.getType();
        List<DocumentDTO> listDocDTO = new ArrayList<DocumentDTO>();
        EntityManager em= emf.createEntityManager();
        try {

            //em.getTransaction().begin();
            Query qryCreditApplication = em.createNativeQuery("select * from credits_aplications where number_request = :creditID limit 1",CreditApplication.class)
                    .setParameter("creditID",creditID);
            CreditApplication creditApplication = (CreditApplication) qryCreditApplication.getResultList()
                    .stream().findFirst().orElse(null);
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

            Query qryDocuments= em.createNativeQuery("select id as idDocument, name,type_document_id as typeDocumentId" +
                            ", number_request as creditAplication,class_document_id as classDocumentId, user_id as userId,status_flag from documents where number_request = :creditID")
                    .setParameter("creditID",creditID)
                    .unwrap( org.hibernate.query.Query.class )
                    .setResultTransformer( Transformers.aliasToBean( DocumentDTO.class ) );

            List<DocumentDTO> documents = qryDocuments.getResultList();

            Query qryDocSpouse = em.createNativeQuery("SELECT id as idDocument, name,type_document_id as typeDocumentId" +
                            ", number_request as creditAplication,class_document_id as classDocumentId, user_id as userId,status_flag  FROM documents WHERE number_request=:creditID" +
                            " and type_document_id in (12,13,14) and status_flag = 1")
                    .setParameter("creditID",creditID).unwrap( org.hibernate.query.Query.class )
                    .setResultTransformer( Transformers.aliasToBean( DocumentDTO.class ) );
            List<DocumentDTO> docSpouse = qryDocSpouse.getResultList();

            Query qryAdditional= em.createNativeQuery("select * from additional_information where number_request = :creditID limit 1",AdditionalInformation.class)
                    .setParameter("creditID",creditID);
            AdditionalInformation additionalInformation = (AdditionalInformation) qryAdditional.getResultList()
                    .stream().findFirst().orElse(null);

            Query qrydependents= em.createNativeQuery("select * from economic_dependents where number_request = :creditID",EconomicDependents.class)
                    .setParameter("creditID",creditID);
            List<EconomicDependents> dependents = qrydependents.getResultList();

            Query qrySpouse= em.createNativeQuery("select * from spouse where number_request = :creditID limit 1",Spouse.class)
                    .setParameter("creditID",creditID);
            Spouse spouse = (Spouse) qrySpouse.getResultList()
                    .stream().findFirst().orElse(null);

            Query qryWork= em.createNativeQuery("SELECT * FROM `work` where number_request = :creditID limit 1",Work.class)
                    .setParameter("creditID",creditID);
            Work work = (Work) qryWork.getResultList()
                    .stream().findFirst().orElse(null);

            Query qryReference = em.createNativeQuery("select * from reference where number_request = :creditID",Reference.class)
                    .setParameter("creditID",creditID);
            List<Reference> references = qryReference.getResultList();

            Query qryProperty= em.createNativeQuery("SELECT * FROM property where number_request = :creditID limit 1",Property.class)
                    .setParameter("creditID",creditID);
            Property property = (Property) qryProperty.getResultList()
                    .stream().findFirst().orElse(null);

            MedicalQuestionnaireAnswerDTO MedicalQuestion = answerMedicalDao.findByCreditID(creditID);

            Query qryCocreditedCustomers= em.createNativeQuery("SELECT * FROM cocredited_customers where number_request = :creditID limit 1",CocreditedCustomers.class)
                    .setParameter("creditID",creditID);
            CocreditedCustomers cocreditedCustomers = (CocreditedCustomers) qryCocreditedCustomers.getResultList()
                    .stream().findFirst().orElse(null);

            Query qryCocreditedAdditional= em.createNativeQuery("SELECT * FROM cocredited_additional where number_request = :creditID limit 1",CocreditedAdditional.class)
                    .setParameter("creditID",creditID);
            CocreditedAdditional cocreditedAdditional = (CocreditedAdditional) qryCocreditedAdditional.getResultList()
                    .stream().findFirst().orElse(null);

            Query qryCocreditedWork= em.createNativeQuery("SELECT * FROM cocredited_work where number_request = :creditID limit 1",CocreditedWork.class)
                    .setParameter("creditID",creditID);
            CocreditedWork cocreditedWork = (CocreditedWork) qryCocreditedWork.getResultList()
                    .stream().findFirst().orElse(null);

            Query qryPdfexpediente = em.createNativeQuery("SELECT true FROM documents "
                            + "WHERE number_request=:creditID "
                            + "AND type_document_id=:idTypeDocument "
                            + "AND status_flag = 1 LIMIT 1")
                    .setParameter("creditID",creditID)
                    .setParameter("idTypeDocument",10);
            Boolean Pdfexpediente =  !qryPdfexpediente.getResultList().isEmpty();

            Query qryPdfSubcuenta = em.createNativeQuery("SELECT true FROM documents "
                            + "WHERE number_request=:creditID "
                            + "AND type_document_id=:idTypeDocument "
                            + "AND status_flag = 1 LIMIT 1")
                    .setParameter("creditID",creditID)
                    .setParameter("idTypeDocument",11);
            Boolean Pdfsubcuenta =  !qryPdfSubcuenta.getResultList().isEmpty();

            items = em.createNamedStoredProcedureQuery("DocumentStatus")
                    .setParameter("request_number",creditID).getResultList();
            //em.getTransaction().commit();
            if (customer != null) {
                detalleCredito.setCustomer(modelMapper.map(customer, CustomerDTO.class));
            }
            detalleCredito.setDocuments(documents);
            if (additionalInformation != null) {
                detalleCredito.setAdditionalies(modelMapper.map(additionalInformation, AdditionalInformationDTO.class));
            }
            if (dependents != null ) {
                if (dependents.size() > 0) {
                    detalleCredito.setDependents(modelMapper.map(dependents, lstTypeEconomic));
                    sizeDepedents = dependents.size();
                }
            }
            if (spouse != null) {
                SpouseDTO spouseDTO = modelMapper.map(spouse, SpouseDTO.class);
                spouseDTO.setImg1(new DocumentDTO());
                spouseDTO.setImg2(new DocumentDTO());
                spouseDTO.setImg3(new DocumentDTO());
                for (DocumentDTO doc:docSpouse) {
                    switch (doc.getTypeDocumentId()){
                        case 12:
                            spouseDTO.setImg1(doc);
                            break;
                        case 13:
                            spouseDTO.setImg2(doc);
                            break;
                        case 14:
                            spouseDTO.setImg3(doc);
                            break;
                    }
                }
                detalleCredito.setSpouse(spouseDTO);
            }
            if(work != null ) {
                detalleCredito.setWork(modelMapper.map(work, WorkDTO.class));
            }
            if (references != null) {
                if (references.size() > 0) {
                    detalleCredito.setReferences(modelMapper.map(references, lstTypeReference));
                    sizeRerefeces = references.size();
                }
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

            pdfDTO.setExpediente(Pdfexpediente);
            pdfDTO.setSubcuenta(Pdfsubcuenta);

            detalleCredito.setPdf(pdfDTO);

            if ( customer != null && additionalInformation != null &&
                work != null && sizeDepedents > 0 && sizeRerefeces > 0
            ) {
                if (additionalInformation.getCivilState() == 1) {
                    if (cocreditedAdditional != null && cocreditedWork != null && cocreditedCustomers != null) {
                        solicitud = true;
                    }
                    if (spouse != null) {
                        solicitud = true;
                    } else {
                        solicitud = false;
                    }
                } else {
                    solicitud = true;
                }
            }
            detalleCredito.setDocumentStatus(items);
            detalleCredito.setSolicitud(solicitud);
            em.close();
            return detalleCredito;
        } catch (Exception Ex) {
            Ex.printStackTrace();
            //em.getTransaction().rollback();
            em.close();
            return detalleCredito;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public DetalleCredito findByDetalleID(Integer creditID) {
        return null;
    }
}
