package com.Xoot.CreditoParaTi.services.interfaces;

import com.Xoot.CreditoParaTi.dto.TypeLaboralActivityDTO;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;
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
