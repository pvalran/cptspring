package com.Xoot.CreditoParaTi.services.interfaces;

import com.Xoot.CreditoParaTi.dto.TypeNationalityDTO;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;
import com.Xoot.CreditoParaTi.entity.app.TypeNationality;

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
