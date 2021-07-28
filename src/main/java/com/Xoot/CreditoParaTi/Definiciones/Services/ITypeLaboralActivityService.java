package com.Xoot.CreditoParaTi.Definiciones.Services;

import com.Xoot.CreditoParaTi.entity.DTO.TypeLaboralActivityDTO;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;
import com.Xoot.CreditoParaTi.entity.TypeLaboralActivity;

import java.util.List;

public interface ITypeLaboralActivityService {
    TypeLaboralActivity findById(Integer id);

    List<TypeLaboralActivity> findAllActive();

    List<TypeLaboralActivity> findByDescription(String Description);

    ResponseDTO save(TypeLaboralActivityDTO catalogo);

    ResponseDTO update(Integer id, TypeLaboralActivityDTO catalogo);

    ResponseDTO active(Integer id);

    ResponseDTO delete(Integer id);

    ResponseDTO getById(Integer id);
}
