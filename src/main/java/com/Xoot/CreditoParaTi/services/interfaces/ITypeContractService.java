package com.Xoot.CreditoParaTi.services.interfaces;

import com.Xoot.CreditoParaTi.dto.TypeContractDTO;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;
import com.Xoot.CreditoParaTi.entity.TypeContract;

import java.util.List;

public interface ITypeContractService {
    TypeContract findById(Integer id);

    List<TypeContract> findAllActive();

    List<TypeContract> findByDescription(String Description);

    ResponseDTO save(TypeContractDTO catalogo);

    ResponseDTO update(Integer id, TypeContractDTO catalogo);

    ResponseDTO active(Integer id);

    ResponseDTO delete(Integer id);

    ResponseDTO getById(Integer id);
}
