package com.Xoot.CreditoParaTi.services.interfaces;

import com.Xoot.CreditoParaTi.dto.CocreditedCustomersDTO;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;
import com.Xoot.CreditoParaTi.entity.AnswerQuestionnaire;
import com.Xoot.CreditoParaTi.entity.CocreditedCustomers;

import java.util.List;

public interface ICocreditedCustomersService {
    CocreditedCustomers findById(Integer id);

    List<CocreditedCustomers> findAllActive();

    ResponseDTO save(CocreditedCustomersDTO ObjDTO);

    ResponseDTO update(Integer id, CocreditedCustomersDTO ObjDTO);

    ResponseDTO active(Integer id);

    ResponseDTO delete(Integer id);
}
