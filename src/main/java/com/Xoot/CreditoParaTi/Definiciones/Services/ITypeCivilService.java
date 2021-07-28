package com.Xoot.CreditoParaTi.Definiciones.Services;

import com.Xoot.CreditoParaTi.entity.DTO.TypeCivilDTO;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;
import com.Xoot.CreditoParaTi.entity.TypeCivil;

import java.util.List;

public interface ITypeCivilService {
    TypeCivil findById(Integer id);

    List<TypeCivil> findAllActive();

    List<TypeCivil> findByDescription(String Description);

    ResponseDTO save(TypeCivilDTO catalogo);

    ResponseDTO update(Integer id, TypeCivilDTO catalogo);

    ResponseDTO active(Integer id);

    ResponseDTO delete(Integer id);

    ResponseDTO getById(Integer id);
}
