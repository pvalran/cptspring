package com.Xoot.CreditoParaTi.models.dao.services;

import com.Xoot.CreditoParaTi.Definiciones.Services.IDetalleCredito;
import com.Xoot.CreditoParaTi.entity.*;
import com.Xoot.CreditoParaTi.entity.DTO.*;
import com.Xoot.CreditoParaTi.models.dao.*;
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
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Service
public class DetalleCreditoImpl implements IDetalleCredito {
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
    private ModelMapper modelMapper;

    private DetalleCredito detalleCredito;

    @Override
    public DetalleCredito findByCreditID(Integer creditID) {
        detalleCredito = new DetalleCredito();
        String base64File;
        Type lstTypeDocuments = new TypeToken<List<DocumentDTO>>() {
        }.getType();
        Type lstTypeEconomic = new TypeToken<List<EconomicDependientiesDto>>() {
        }.getType();
        Type lstTypeReference = new TypeToken<List<ReferenceDTO>>() {
        }.getType();

        List<DocumentDTO> listDocDTO = new ArrayList<DocumentDTO>();


        Customer customer = customerDao.findByCreditId(creditID);
        List<Document> documents = documentDao.findByCreditId(creditID);
        List<Document> docSpouse = documentDao.findByCreditIdSpouse(creditID);
        AdditionalInformation additionalInformation = additionalInformationDao.findByCreditId(creditID);
        List<EconomicDependents> dependents = economicDependientiesDAO.findByCreditId(creditID);
        Spouse spouse = spouseDao.findByCreditId(creditID);
        Work work = workDao.findByCreditId(creditID);
        List<Reference> references = referenceDao.findByCreditId(creditID);

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
                /*Path rutaArchivo = Paths.get("/srv/www/upload").resolve(doc.getName()).toAbsolutePath();
                File file = rutaArchivo.toFile();
                try {
                    byte[] fileContent = FileUtils.readFileToByteArray(file);
                    base64File = Base64.getEncoder().encodeToString(fileContent);
                } catch (IOException e) {
                    base64File = null;
                }*/

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
        return detalleCredito;
    }
}
