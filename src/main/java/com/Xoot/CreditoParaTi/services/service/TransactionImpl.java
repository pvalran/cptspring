package com.Xoot.CreditoParaTi.services.service;

import com.Xoot.CreditoParaTi.dto.CustomerDTO;
import com.Xoot.CreditoParaTi.dto.CustomerTransactionDTO;
import com.Xoot.CreditoParaTi.entity.app.AdditionalInformation;
import com.Xoot.CreditoParaTi.entity.app.CreditApplication;
import com.Xoot.CreditoParaTi.entity.app.Customer;
import com.Xoot.CreditoParaTi.entity.app.transaction;
import com.Xoot.CreditoParaTi.entity.pima.PimaTransaction;
import com.Xoot.CreditoParaTi.mapper.FilterTransacionDTO;
import com.Xoot.CreditoParaTi.repositories.app.IAdditionalInformationDao;
import com.Xoot.CreditoParaTi.repositories.app.ICreditApplicationDao;
import com.Xoot.CreditoParaTi.repositories.app.ICustomerDao;
import com.Xoot.CreditoParaTi.repositories.pima.IPimaTransactionDao;
import com.Xoot.CreditoParaTi.services.interfaces.ITransactionService;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;
import com.Xoot.CreditoParaTi.dto.TransactionDTO;
import com.Xoot.CreditoParaTi.repositories.app.ITransactionDao;
import com.Xoot.CreditoParaTi.utils.TransactionUtil;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import org.json.JSONException;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class TransactionImpl implements ITransactionService {
    public Object data = null;
    public Boolean result = false;
    public String message;

    @Autowired
    ICustomerDao _customerDao;

    @Autowired
    ITransactionDao transactionDao;

    @Autowired
    IAdditionalInformationDao additionalInformationDao;


    @Autowired
    ICreditApplicationDao creditApplicationDao;

    @Autowired
    IPimaTransactionDao PimaTransactionDao;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public transaction findById(Integer id) {
        return transactionDao.findById(id).orElse(null);
    }

    @Override
    public List<transaction> findAllActive() {
        return transactionDao.findAllActive();
    }

    @Override
    public List<transaction> findByUser(Integer userId) {
        return transactionDao.findByUser(userId);
    }

    @Override
    public ResponseDTO save(TransactionDTO transactionDTO) {
        Gson ObjJson = new Gson();
        Integer status = 1;
        LinkedTreeMap transCode = ObjJson.fromJson(transactionDTO.getTransactionCode(), LinkedTreeMap.class);
        switch (transactionDTO.getTransactionType()){
            case 1:
            case 4:
            case 7:
                if (transCode.containsKey("error")) {
                    status = 2;
                } else {
                    status = 1;
                }
                break;
            case 2:
            case 5:
                if (transCode.containsKey("estatus")) {
                    if (transCode.get("estatus").toString().toLowerCase().equals("ok")) {
                        status = 1;
                    } else {
                        status = 2;
                    }
                } else {
                    status = 2;
                }
                break;
            case 3:
            case 6:
            case 8:
                if (transCode.containsKey("estatus")) {
                    if (transCode.get("estatus").toString().toLowerCase().equals("ok")) {
                        status = 1;
                    } else {
                        status = 2;
                    }
                } else {
                    status = 2;
                }
                break;
        }
        transactionDTO.setTransactionStatus(status);
        transaction ObjTrans = transactionDao.save(modelMapper.map(transactionDTO,transaction.class));
        if (ObjTrans != null) {
            PimaTransaction PimaTransPima = PimaTransactionDao.save(modelMapper.map(transactionDTO, PimaTransaction.class));
            if (PimaTransPima != null) {
                return new ResponseDTO(ObjTrans, "Registro creado", true);
            } else {
                transactionDao.delete(ObjTrans);
                return  new ResponseDTO(ObjTrans,"Error en registro",false);
            }
        }
        return  new ResponseDTO(ObjTrans,"Error en registro",false);
    }


    @Override
    public ResponseDTO update(Integer id, TransactionDTO transactionDTO) {
        transaction entity = transactionDao.findById(id).orElse(null);
        if (entity != null) {
            entity.setMdfd_on(new Date());
            modelMapper.getConfiguration().setSkipNullEnabled(true)
                    .setCollectionsMergeEnabled(false)
                    .setMatchingStrategy(MatchingStrategies.STRICT);
            modelMapper.map(transactionDTO,entity);
            transactionDao.save(entity);
            transactionDTO = modelMapper.map(entity, TransactionDTO.class);
            return new ResponseDTO(transactionDTO,"Modificaciòn realizada con exito",true);
        }
        return new ResponseDTO(transactionDTO,"Error en la modificación del registro",false);
    }

    @Override
    public ResponseDTO active(Integer id) {
        return null;
    }

    @Override
    public ResponseDTO delete(Integer id) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transaction entity = transactionDao.findById(id).orElse(null);
        if (entity != null) {
            entity.setMdfd_on(new Date());
            entity.setStatus_flag(0);
            transactionDao.save(entity);
            transactionDTO = modelMapper.map(entity, TransactionDTO.class);
            return new ResponseDTO(transactionDTO,"Eliminación realizada con exito",true);
        }
        return new ResponseDTO(transactionDTO,"Error en la elimanación del registro",false);
    }

    @Override
    public ResponseDTO filterDate(FilterTransacionDTO filterTransacionDTO) {
        TransactionUtil ObjTransUtil = new TransactionUtil();
        List<CreditApplication> creditApplications = creditApplicationDao.getCreditTransDate(
                filterTransacionDTO.getStartdate(),
                filterTransacionDTO.getEnddate());

        List<CustomerTransactionDTO> customerTransactions;
        HashMap<Integer,String> map = new HashMap<>();
        map.put (1, "A");
        map.put (2, "R");


        if ((filterTransacionDTO.getStatus().isEmpty()) ||
                (filterTransacionDTO.getStatus() == null)) {
            filterTransacionDTO.setStatus("A,R,P");
        }

        customerTransactions = new ArrayList<CustomerTransactionDTO>();
        for(CreditApplication creditApplication:creditApplications){
            if (creditApplication.getCustomer() != null ){
                CustomerTransactionDTO customerTransactionDTO = new CustomerTransactionDTO();
                customerTransactionDTO.setCreditId(creditApplication.getCreditId());
                Customer customer = _customerDao.findById(creditApplication.getCustomer()).orElse(null);
                customerTransactionDTO.setCustomer(
                        modelMapper.map(customer, CustomerDTO.class)
                );

                AdditionalInformation additional =  additionalInformationDao.findByCreditId(creditApplication.getCreditId());
                List<transaction> transactions = transactionDao.findByCreditID(creditApplication.getCreditId());
                customerTransactionDTO.setLayerDocument("");
                customerTransactionDTO.setLayerBiometric("");
                customerTransactionDTO.setLayerGobernment("");
                for (transaction Transaction : transactions) {
                    switch (Transaction.getTransactionType()) {
                        case 1:
                            customerTransactionDTO.setLayerDocument(map.get(Transaction.getTransactionStatus()));
                            break;
                        case 2:
                            customerTransactionDTO.setLayerBiometric(map.get(Transaction.getTransactionStatus()));
                            break;
                        case 3:
                            customerTransactionDTO.setLayerGobernment(map.get(Transaction.getTransactionStatus()));
                            break;
                    }
                }

                if (customerTransactionDTO.getLayerDocument() == "R" || customerTransactionDTO.getLayerBiometric() == "R") {
                    customerTransactionDTO.setStatus("R");
                } else if (customerTransactionDTO.getLayerDocument() == "A" && customerTransactionDTO.getLayerBiometric() == "A") {
                    if (customerTransactionDTO.getLayerGobernment() == "A") {
                        customerTransactionDTO.setStatus("A");
                    } else {
                        customerTransactionDTO.setStatus("P");
                    }
                } else {
                    customerTransactionDTO.setStatus("R");
                }
                if (additional != null) {
                    customerTransactionDTO.setMobile(additional.getMobile());
                } else {
                    customerTransactionDTO.setMobile("");
                }

                customerTransactionDTO.setCrtd_on(creditApplication.getCrtd_on());
                if (filterTransacionDTO.getStatus().toLowerCase().contains(customerTransactionDTO.getStatus().toLowerCase())) {
                    customerTransactions.add(customerTransactionDTO);
                }
            }
        }

        data = customerTransactions;
        result = true;
        message = "Exito";
        return new ResponseDTO(data, message, result);
    }

    @Override
    public ResponseDTO filterSearch(FilterTransacionDTO filterTransacionDTO) {
        TransactionUtil ObjTransUtil = new TransactionUtil();
        List<CreditApplication> creditApplications = creditApplicationDao.getCreditTransSearch(
                filterTransacionDTO.getSearch());

        List<CustomerTransactionDTO> customerTransactions;
        HashMap<Integer,String> map = new HashMap<>();
        map.put (1, "A");
        map.put (2, "R");

        if ((filterTransacionDTO.getStatus().isEmpty()) ||
                (filterTransacionDTO.getStatus() == null)) {
            filterTransacionDTO.setStatus("A,R,P");
        }

        customerTransactions = new ArrayList<CustomerTransactionDTO>();
        for(CreditApplication creditApplication:creditApplications){
            if (creditApplication.getCustomer() != null ){
                CustomerTransactionDTO customerTransactionDTO = new CustomerTransactionDTO();
                customerTransactionDTO.setCreditId(creditApplication.getCreditId());
                Customer customer = _customerDao.findById(creditApplication.getCustomer()).orElse(null);
                customerTransactionDTO.setCustomer(
                        modelMapper.map(customer, CustomerDTO.class)
                );
                AdditionalInformation additional =  additionalInformationDao.findByCreditId(creditApplication.getCreditId());
                List<transaction> transactions = transactionDao.findByCreditID(creditApplication.getCreditId());
                customerTransactionDTO.setLayerDocument("");
                customerTransactionDTO.setLayerBiometric("");
                customerTransactionDTO.setLayerGobernment("");
                for (transaction Transaction : transactions) {
                    switch (Transaction.getTransactionType()) {
                        case 1:
                            customerTransactionDTO.setLayerDocument(map.get(Transaction.getTransactionStatus()));
                            break;
                        case 2:
                            customerTransactionDTO.setLayerBiometric(map.get(Transaction.getTransactionStatus()));
                            break;
                        case 3:
                            customerTransactionDTO.setLayerGobernment(map.get(Transaction.getTransactionStatus()));
                            break;
                    }
                }

                if (customerTransactionDTO.getLayerDocument() == "R" || customerTransactionDTO.getLayerBiometric() == "R") {
                    customerTransactionDTO.setStatus("R");
                } else if (customerTransactionDTO.getLayerDocument() == "A" && customerTransactionDTO.getLayerBiometric() == "A") {
                    if (customerTransactionDTO.getLayerGobernment() == "A") {
                        customerTransactionDTO.setStatus("A");
                    } else {
                        customerTransactionDTO.setStatus("P");
                    }
                } else {
                    customerTransactionDTO.setStatus("R");
                }
                if (additional != null) {
                    customerTransactionDTO.setMobile(additional.getMobile());
                } else {
                    customerTransactionDTO.setMobile("");
                }
                customerTransactionDTO.setCrtd_on(creditApplication.getCrtd_on());
                if (filterTransacionDTO.getStatus().toLowerCase().contains(customerTransactionDTO.getStatus().toLowerCase())) {
                    customerTransactions.add(customerTransactionDTO);
                }
            }
        }

        data = customerTransactions;
        result = true;
        message = "Exito";
        return new ResponseDTO(data, message, result);
    }

    @Override
    public ResponseDTO filterStatistics(FilterTransacionDTO filterTransacionDTO) {

        data = "";
        result = true;
        message = "Exito";
        return new ResponseDTO(data, message, result);

    }

    @Override
    @Transactional(readOnly = false)
    public ResponseDTO findValidate(Integer creditId, Integer typeTransaction) {
        transaction Transaction = transactionDao.findValidate(creditId,typeTransaction);
        try {
            Gson ObjJson = new Gson();
            Object Obj = ObjJson.fromJson(Transaction.getTransactionCode(),Object.class);
            return new ResponseDTO(Obj, "Exito", true);
        }catch (JSONException err){
            return new ResponseDTO(null, "Error", false);
        }
    }
}
