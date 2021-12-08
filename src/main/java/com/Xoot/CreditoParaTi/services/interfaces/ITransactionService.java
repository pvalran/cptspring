package com.Xoot.CreditoParaTi.services.interfaces;

import com.Xoot.CreditoParaTi.dto.ResponseDTO;
import com.Xoot.CreditoParaTi.dto.TransactionDTO;
import com.Xoot.CreditoParaTi.entity.app.transaction;
import com.Xoot.CreditoParaTi.mapper.FilterTransacionDTO;

import java.util.List;

public interface ITransactionService {
    transaction findById(Integer id);

    List<transaction> findAllActive();

    List<transaction> findByUser(Integer User);

    ResponseDTO save(TransactionDTO transactionDTO);

    ResponseDTO update(Integer id, TransactionDTO transactionDTO );

    ResponseDTO active(Integer id);

    ResponseDTO delete(Integer id);

    ResponseDTO filterDate(FilterTransacionDTO filterTransacionDTO);

    ResponseDTO filterSearch(FilterTransacionDTO filterTransacionDTO);

    ResponseDTO filterStatistics(FilterTransacionDTO filterTransacionDTO);

    ResponseDTO findValidate(Integer creditId,Integer typeTransaction);
}
