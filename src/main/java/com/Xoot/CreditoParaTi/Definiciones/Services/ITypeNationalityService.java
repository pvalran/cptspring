package com.Xoot.CreditoParaTi.Definiciones.Services;

import com.Xoot.CreditoParaTi.entity.DTO.TypeNationalityDTO;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;
import com.Xoot.CreditoParaTi.entity.TypeNationality;

import java.util.List;

public interface ITypeNationalityService {
    TypeNationality findById(Integer id);

    List<TypeNationality> findAllActive();

    List<TypeNationality>  findByDescription(String Description);

    ResponseDTO save(TypeNationalityDTO catalogo);

    ResponseDTO update(Integer id, TypeNationalityDTO catalogo);

    ResponseDTO active(Integer id);

    ResponseDTO delete(Integer id);

    ResponseDTO getById(Integer id);
}
