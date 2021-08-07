package com.Xoot.CreditoParaTi.Definiciones.Services;

import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;
import com.Xoot.CreditoParaTi.entity.DTO.TransactionDTO;
import com.Xoot.CreditoParaTi.entity.transaction;

import java.util.List;

public interface ITransactionService {
    transaction findById(Integer id);

    List<transaction> findAllActive();

    List<transaction> findByUser(Integer User);

    ResponseDTO save(TransactionDTO transactionDTO);

    ResponseDTO update(Integer id, TransactionDTO transactionDTO );

    ResponseDTO active(Integer id);

    ResponseDTO delete(Integer id);
}
