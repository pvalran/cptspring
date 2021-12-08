package com.Xoot.CreditoParaTi.utils;

import com.Xoot.CreditoParaTi.dto.CustomerDTO;
import com.Xoot.CreditoParaTi.dto.CustomerTransactionDTO;
import com.Xoot.CreditoParaTi.entity.app.CreditApplication;
import com.Xoot.CreditoParaTi.entity.app.Customer;
import com.Xoot.CreditoParaTi.entity.app.transaction;
import com.Xoot.CreditoParaTi.repositories.app.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class TransactionUtil {
    public Object data = null;
    public Boolean result = false;
    public String message;

    @Autowired
    private ICustomerDao _customerDao;
    @Autowired
    private ICustomerGenderDao _customerGenderDao;
    @Autowired
    private ILocationStateDao _locationStateDao;
    @Autowired
    private ILocationSuburbDao _locationSuburbDao;
    @Autowired
    private ITransactionDao transactionDao;
    @Autowired
    private  ICreditApplicationDao creditApplicationDao;


    @Autowired
    private ModelMapper modelMapper;




    public List<CustomerTransactionDTO> creditTransaction(List<CreditApplication> creditApplications){
        List<CustomerTransactionDTO> customerTransactions;
        HashMap<Integer,String> map = new HashMap<>();
        map.put (1, "A");
        map.put (2, "R");

        customerTransactions = new ArrayList<CustomerTransactionDTO>();
        for(CreditApplication creditApplication:creditApplications){
            if (creditApplication.getCustomer() > 0 && creditApplication.getCustomer() != null ){
                CustomerTransactionDTO customerTransactionDTO = new CustomerTransactionDTO();
                Customer customer = _customerDao.findById(creditApplication.getCustomer()).orElse(null);
                customerTransactionDTO.setCustomer(
                        modelMapper.map(customer, CustomerDTO.class)
                );
                List<transaction> transactions = transactionDao.findByCreditID(creditApplication.getCreditId());
                for (transaction Transaction : transactions) {
                    customerTransactionDTO.setLayerDocument("");
                    customerTransactionDTO.setLayerBiometric("");
                    customerTransactionDTO.setLayerGobernment("");
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
                customerTransactions.add(customerTransactionDTO);
            }
        }
        return customerTransactions;
    }
}
