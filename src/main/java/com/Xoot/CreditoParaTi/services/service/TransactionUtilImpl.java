package com.Xoot.CreditoParaTi.services.service;

import com.Xoot.CreditoParaTi.dto.CustomerDTO;
import com.Xoot.CreditoParaTi.dto.CustomerTransactionDTO;


import com.Xoot.CreditoParaTi.dto.DetalleCredito;
import com.Xoot.CreditoParaTi.entity.*;
import com.Xoot.CreditoParaTi.mapper.FilterTransacionDTO;

import com.Xoot.CreditoParaTi.repositories.interfaces.*;
import com.Xoot.CreditoParaTi.services.interfaces.ITransactionUtilService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class TransactionUtilImpl implements ITransactionUtilService {
    public Object data = null;
    public Boolean result = false;
    public String message;

    @Autowired
    ICustomerDao _customerDao;

    @Autowired
    ITransactionDao transactionDao;

    @Autowired
    ISpouseDao spouseDao;

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
    DetalleCreditoImpl detalleCredito;

    @Autowired
    ModelMapper modelMapper;



    public  List<CustomerTransactionDTO> creditTransaction (List<CreditApplication> creditApplications, FilterTransacionDTO filterTransacionDTO){
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
                    if (detalleCredito.findBySolictud(creditApplication.getCreditId())) {
                        customerTransactionDTO.setSolicitud(1);
                    } else {
                        customerTransactionDTO.setSolicitud(0);
                    }
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
                    Spouse spouse = spouseDao.findByCreditId(creditApplication.getCreditId());
                    if (spouse != null){
                        customerTransactionDTO.getCustomer().setName(spouse.getName());
                        customerTransactionDTO.getCustomer().setPaternalLastName(spouse.getPaternalLastName());
                        customerTransactionDTO.getCustomer().setMotherLastName(spouse.getMaternalLastName());
                    } else {
                        customerTransactionDTO.getCustomer().setName("");
                        customerTransactionDTO.getCustomer().setPaternalLastName("");
                        customerTransactionDTO.getCustomer().setMotherLastName("");
                    }
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
                    if (cocreditedCustomers != null){
                        customerTransactionDTO.getCustomer().setName(cocreditedCustomers.getName());
                        customerTransactionDTO.getCustomer().setPaternalLastName(cocreditedCustomers.getPaternalLastName());
                        customerTransactionDTO.getCustomer().setMotherLastName(cocreditedCustomers.getMotherLastName());
                    } else {
                        customerTransactionDTO.getCustomer().setName("");
                        customerTransactionDTO.getCustomer().setPaternalLastName("");
                        customerTransactionDTO.getCustomer().setMotherLastName("");
                    }
                    email = (cocreditedCustomers != null)? cocreditedCustomers.getEmail() : null;
                    mobile = (cocreditedAdditional != null)? cocreditedAdditional.getMobile() : null;
                    customerTransactionDTO.setEmail(email);
                    customerTransactionDTO.setMobile(mobile);
                    customerTransactionDTO.setEnrolment("Coacreditado");
                    customerTransactionDTO.setSolicitud(0);
                    customerTransactions.add(customerTransactionDTO);
                }
            }
        }
        return customerTransactions;
    }

    public CustomerTransactionDTO customerTransaction(
            FilterTransacionDTO filterTransacionDTO,
            CreditApplication creditApplication,
            List<Integer> typeTransaction
    ) {
        HashMap<Integer,String> map = new HashMap<>();
        String email;
        String mobile;
        Customer customer;
        map.put (1, "A");
        map.put (2, "R");

        CustomerTransactionDTO customerTransactionDTO = new CustomerTransactionDTO();
        customerTransactionDTO.setCreditId(creditApplication.getCreditId());
        if (creditApplication.getCustomer() != null) {
            customer = _customerDao.findById(creditApplication.getCustomer()).orElse(null);
        } else {
            customer = null;
        }

        customerTransactionDTO.setCustomer(modelMapper.map(customer, CustomerDTO.class));
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

        if (customerTransactionDTO.getLayerDocument().equals("R") || customerTransactionDTO.getLayerBiometric().equals("R")) {
            customerTransactionDTO.setStatus("R");
        } else if (customerTransactionDTO.getLayerDocument().equals("A") && customerTransactionDTO.getLayerBiometric().equals("A")) {
            if (customerTransactionDTO.getLayerGobernment().equals("A")) {
                customerTransactionDTO.setStatus("A");
            } else {
                customerTransactionDTO.setStatus("P");
            }
        } else {
            Integer[] array = {7, 8};
            List<Integer> list = Arrays.asList(array);
            if (customerTransactionDTO.getLayerDocument().equals("A") &&
                customerTransactionDTO.getLayerGobernment().equals("A") &&
                    typeTransaction.containsAll(list)
            ){
                customerTransactionDTO.setStatus("A");
            } else {
                customerTransactionDTO.setStatus("R");
            }
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
