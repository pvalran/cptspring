package com.Xoot.CreditoParaTi.Definiciones.Services;

import com.Xoot.CreditoParaTi.entity.DTO.TypeMaritalStatusDTO;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;
import com.Xoot.CreditoParaTi.entity.TypeMaritalStatus;

import java.util.List;

public interface ITypeMaritalStatusService {
    TypeMaritalStatus findById(Integer id);

    List<TypeMaritalStatus> findAllActive();

    List<TypeMaritalStatus> findByDescription(String Description);

    ResponseDTO save(TypeMaritalStatusDTO catalogo);

    ResponseDTO update(Integer id, TypeMaritalStatusDTO catalogo);

    ResponseDTO active(Integer id);

    ResponseDTO delete(Integer id);

    ResponseDTO getById(Integer id);
}
