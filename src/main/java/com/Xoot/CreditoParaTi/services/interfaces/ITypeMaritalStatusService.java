package com.Xoot.CreditoParaTi.services.interfaces;

import com.Xoot.CreditoParaTi.dto.TypeMaritalStatusDTO;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;
import com.Xoot.CreditoParaTi.entity.app.TypeMaritalStatus;

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
