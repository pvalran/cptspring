package com.Xoot.CreditoParaTi.Definiciones.Services;

import com.Xoot.CreditoParaTi.entity.DTO.TypeContractDTO;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;
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
