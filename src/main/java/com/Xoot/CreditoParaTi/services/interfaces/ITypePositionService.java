package com.Xoot.CreditoParaTi.services.interfaces;

import com.Xoot.CreditoParaTi.dto.TypePositionDTO;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;
import com.Xoot.CreditoParaTi.entity.app.TypePosition;

import java.util.List;

public interface ITypePositionService {
    TypePosition findById(Integer id);

    List<TypePosition> findAllActive();

    List<TypePosition> findByDescription(String Description);

    ResponseDTO save(TypePositionDTO catalogo);

    ResponseDTO update(Integer id, TypePositionDTO catalogo);

    ResponseDTO active(Integer id);

    ResponseDTO delete(Integer id);

    ResponseDTO getById(Integer id);
}
