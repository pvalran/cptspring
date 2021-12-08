package com.Xoot.CreditoParaTi.services.interfaces;

import com.Xoot.CreditoParaTi.dto.CustomerTransactionDTO;
import com.Xoot.CreditoParaTi.entity.app.CreditApplication;
import com.Xoot.CreditoParaTi.mapper.FilterTransacionDTO;

import java.util.List;

public interface ITransactionUtilService {
    public List<CustomerTransactionDTO> creditTransaction (List<CreditApplication> creditApplications, FilterTransacionDTO filterTransacionDTO);
    public CustomerTransactionDTO customerTransaction(
            FilterTransacionDTO filterTransacionDTO,
            CreditApplication creditApplication,
            List<Integer> typeTransaction
    );

}
