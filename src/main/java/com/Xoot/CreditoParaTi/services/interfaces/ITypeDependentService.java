package com.Xoot.CreditoParaTi.services.interfaces;

import com.Xoot.CreditoParaTi.dto.ResponseDTO;
import com.Xoot.CreditoParaTi.dto.TypeDependentDTO;
import com.Xoot.CreditoParaTi.entity.app.TypeDependent;

import java.util.List;

public interface ITypeDependentService {
    TypeDependent findById(Integer id);

    List<TypeDependent> findAllActive();

    List<TypeDependent> findByDescription(String Description);

    List<TypeDependent> findByCivil(Integer id);

    TypeDependent save(TypeDependentDTO catalogo);

    ResponseDTO update(Integer id, TypeDependentDTO catalogo);

    ResponseDTO active(Integer id);

    ResponseDTO delete(Integer id);

    ResponseDTO getById(Integer id);
}

