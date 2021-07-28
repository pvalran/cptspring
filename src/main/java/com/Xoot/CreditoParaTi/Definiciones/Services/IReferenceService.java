package com.Xoot.CreditoParaTi.Definiciones.Services;

import com.Xoot.CreditoParaTi.entity.Reference;
import com.Xoot.CreditoParaTi.entity.DTO.ReferenceDTO;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;

import java.util.List;

public interface IReferenceService {
    Reference findById(Integer id);

    List<Reference> findAllActive();

    ResponseDTO save(ReferenceDTO referenceDTO);

    ResponseDTO update(Integer id, ReferenceDTO referenceDTO);

    ResponseDTO active(Integer id);

    ResponseDTO delete(Integer id);
}
