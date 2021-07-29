package com.Xoot.CreditoParaTi.models.dao.services;

import com.Xoot.CreditoParaTi.Definiciones.Services.IDetalleCredito;
import com.Xoot.CreditoParaTi.entity.*;
import com.Xoot.CreditoParaTi.entity.DTO.*;
import com.Xoot.CreditoParaTi.models.dao.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
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
        Type lstTypeDocuments = new TypeToken<List<DocumentDTO>>() {}.getType();
        Type lstTypeEconomic = new TypeToken<List<EconomicDependientiesDto>>() {}.getType();
        Type lstTypeReference = new TypeToken<List<ReferenceDTO>>() {}.getType();



        Customer customer = customerDao.findByCreditId(creditID);
        List<Document> documents = documentDao.findByCreditId(creditID);
        AdditionalInformation additionalInformation = additionalInformationDao.findByCreditId(creditID);
        List<EconomicDependents> dependents = economicDependientiesDAO.findByCreditId(creditID);
        Spouse spouse = spouseDao.findByCreditId(creditID);
        Work work = workDao.findByCreditId(creditID);
        List<Reference> references = referenceDao.findByCreditId(creditID);

        detalleCredito.setCustomer(modelMapper.map(customer, CustomerDTO.class));
        detalleCredito.setDocuments(modelMapper.map(documents,lstTypeDocuments));
        if (additionalInformation != null) {
            detalleCredito.setAdditionalies(modelMapper.map(additionalInformation, AdditionalInformationDTO.class));
        }

        if (dependents != null ) {
            detalleCredito.setDependents(modelMapper.map(dependents, lstTypeEconomic));
        }
        if (spouse != null) {
            detalleCredito.setSpouse(modelMapper.map(spouse, SpouseDTO.class));
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
