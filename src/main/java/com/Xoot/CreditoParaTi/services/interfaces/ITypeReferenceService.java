package com.Xoot.CreditoParaTi.services.interfaces;

import com.Xoot.CreditoParaTi.dto.TypeReferenceDTO;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;
import com.Xoot.CreditoParaTi.entity.TypeReference;

import java.util.List;

public interface ITypeReferenceService {
    TypeReference findById(Integer id);

    List<TypeReference> findAllActive();

    List<TypeReference> findByDescription(String Description);

    TypeReference save(TypeReferenceDTO catalogo);

    ResponseDTO update(Integer id, TypeReferenceDTO catalogo);

    ResponseDTO active(Integer id);

    ResponseDTO delete(Integer id);

    ResponseDTO getById(Integer id);
}
