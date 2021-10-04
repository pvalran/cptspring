package com.Xoot.CreditoParaTi.services.service;

import com.Xoot.CreditoParaTi.dto.CustomerDTO;
import com.Xoot.CreditoParaTi.dto.CustomerTransactionDTO;
import com.Xoot.CreditoParaTi.entity.*;
import com.Xoot.CreditoParaTi.mapper.FilterTransacionDTO;
import com.Xoot.CreditoParaTi.repositories.interfaces.*;
import com.Xoot.CreditoParaTi.services.interfaces.ITransactionService;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;
import com.Xoot.CreditoParaTi.dto.TransactionDTO;
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
    IUserDao userDao;

    @Autowired
    IAdditionalInformationDao additionalInformationDao;


    @Autowired
    ICreditApplicationDao creditApplicationDao;

    @Autowired
    ICocreditedCustomersDao cocreditedCustomersDao;

    @Autowired
    ICocreditedAdditionalDao cocreditedAdditionalDao;

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
                if (transCode.containsKey("error")) {
                    status = 2;
                } else {
                    status = 1;
                }
                break;
            case 2:
                if (transCode.get("estatus").toString().toLowerCase().equals("ok") ) {
                    status = 1;
                } else {
                    status = 2;
                }
                break;
            case 3:
                if (transCode.get("estatus").toString().toLowerCase().equals("ok") ) {
                    status = 1;
                } else {
                    status = 2;
                }
                break;
        }
        transactionDTO.setTransactionStatus(status);
        transaction ObjTrans = transactionDao.save(modelMapper.map(transactionDTO,transaction.class));
        if (ObjTrans != null) {
            return  new ResponseDTO(ObjTrans,"Registro creado",true);
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
    public  List<CustomerTransactionDTO> filterDate(FilterTransacionDTO filterTransacionDTO) {
        String email;
        String mobile;
        TransactionUtilImpl ObjTransUtil = new TransactionUtilImpl();
        CustomerTransactionDTO customerTransactionDTO;
        List<CreditApplication> creditApplications = creditApplicationDao.getCreditTransDate(
                filterTransacionDTO.getStartdate(),
                filterTransacionDTO.getEnddate());

        List<CustomerTransactionDTO> customerTransactions;
        if ((filterTransacionDTO.getStatus().isEmpty()) ||
                (filterTransacionDTO.getStatus() == null)) {
            filterTransacionDTO.setStatus("A,R,P");
        }
        customerTransactions = creditTransaction(creditApplications,filterTransacionDTO);
        return customerTransactions;
    }

    @Override
    public  List<CustomerTransactionDTO> filterSearch(FilterTransacionDTO filterTransacionDTO) {
        TransactionUtilImpl ObjTransUtil = new TransactionUtilImpl();
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
        customerTransactions = creditTransaction(creditApplications,filterTransacionDTO);
        return customerTransactions;
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


    private  List<CustomerTransactionDTO> creditTransaction (List<CreditApplication> creditApplications,FilterTransacionDTO filterTransacionDTO){
        List<CustomerTransactionDTO> customerTransactions = new ArrayList<CustomerTransactionDTO>();
        CustomerTransactionDTO customerTransactionDTO;
        String email;
        String mobile;
        for(CreditApplication creditApplication:creditApplications){
            if (creditApplication.getCustomer() != null ){
                List<Integer> typeTransaction = new ArrayList<Integer>();
                typeTransaction.add(1);
                typeTransaction.add(2);
                typeTransaction.add(3);
                customerTransactionDTO = customerTransaction(filterTransacionDTO,
                        creditApplication,
                        typeTransaction);
                if (customerTransactionDTO != null) {
                    customerTransactionDTO.setEnrolment("Acreditado");
                    customerTransactions.add(customerTransactionDTO);
                }
                typeTransaction.clear();
                typeTransaction.add(4);
                typeTransaction.add(5);
                typeTransaction.add(6);
                customerTransactionDTO = customerTransaction(filterTransacionDTO,
                        creditApplication,
                        typeTransaction);
                if (customerTransactionDTO != null) {
                    customerTransactionDTO.setEmail("");
                    customerTransactionDTO.setMobile("");
                    customerTransactionDTO.setEnrolment("Cónyuge");
                    customerTransactions.add(customerTransactionDTO);
                }
                typeTransaction.clear();
                typeTransaction.add(7);
                typeTransaction.add(8);
                customerTransactionDTO = customerTransaction(filterTransacionDTO,
                        creditApplication,
                        typeTransaction);
                if (customerTransactionDTO != null) {
                    CocreditedCustomers cocreditedCustomers = cocreditedCustomersDao.findByCreditId(creditApplication.getCreditId());
                    CocreditedAdditional cocreditedAdditional = cocreditedAdditionalDao.findByCreditId(creditApplication.getCreditId());
                    email = (cocreditedCustomers != null)? cocreditedCustomers.getEmail() : null;
                    mobile = (cocreditedAdditional != null)? cocreditedAdditional.getMobile() : null;
                    customerTransactionDTO.setEmail(email);
                    customerTransactionDTO.setMobile(mobile);
                    customerTransactionDTO.setEnrolment("Coacreditado");
                    customerTransactions.add(customerTransactionDTO);
                }
            }
        }
        return customerTransactions;
    }

    private CustomerTransactionDTO customerTransaction(
        FilterTransacionDTO filterTransacionDTO,
        CreditApplication creditApplication,
        List<Integer> typeTransaction
    ) {
        HashMap<Integer,String> map = new HashMap<>();
        String email;
        String mobile;
        map.put (1, "A");
        map.put (2, "R");

        CustomerTransactionDTO customerTransactionDTO = new CustomerTransactionDTO();
        customerTransactionDTO.setCreditId(creditApplication.getCreditId());
        Customer customer = _customerDao.findById(creditApplication.getCustomer()).orElse(null);
        customerTransactionDTO.setCustomer(
                modelMapper.map(customer, CustomerDTO.class)
        );
        AdditionalInformation additional =  additionalInformationDao.findByCreditId(creditApplication.getCreditId());
        List<transaction> transactions = transactionDao.findByCreditID(creditApplication.getCreditId(),typeTransaction);
        if (transactions.size() == 0) {
            return null;
        }
        email = (customer != null)? customer.getEmail() : null;
        mobile = (additional != null)? additional.getMobile() : null;
        customerTransactionDTO.setEmail(email);
        customerTransactionDTO.setMobile(mobile);
        customerTransactionDTO.setLayerDocument("");
        customerTransactionDTO.setLayerBiometric("");
        customerTransactionDTO.setLayerGobernment("");
        for (transaction Transaction : transactions) {
            switch (Transaction.getTransactionType()) {
                case 1:
                case 4:
                case 7:
                    customerTransactionDTO.setLayerDocument(map.get(Transaction.getTransactionStatus()));
                    break;
                case 2:
                case 5:
                    customerTransactionDTO.setLayerBiometric(map.get(Transaction.getTransactionStatus()));
                    break;
                case 3:
                case 6:
                case 8:
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
        Usuario userCreated = userDao.findById(creditApplication.getUser()).orElse(new Usuario());
        customerTransactionDTO.setCrtd_on(creditApplication.getCrtd_on());
        customerTransactionDTO.setCrtd_by(userCreated.getCrtd_by());
        if (filterTransacionDTO.getStatus().toLowerCase().contains(customerTransactionDTO.getStatus().toLowerCase())) {
            return customerTransactionDTO;
        } else {
            return  null;
        }
    }
}
