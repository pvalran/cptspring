package com.Xoot.CreditoParaTi.services.interfaces;

import com.Xoot.CreditoParaTi.dto.TypeDatosDTO;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;
import com.Xoot.CreditoParaTi.entity.app.TypeDatos;

import java.util.List;

public interface ITypeDatosService {
    TypeDatos findById(Integer id);

    List<TypeDatos> findAllActive();

    List<TypeDatos> findByDescription(String Description);

    ResponseDTO save(TypeDatosDTO catalogo);

    ResponseDTO update(Integer id, TypeDatosDTO catalogo);

    ResponseDTO active(Integer id);

    ResponseDTO delete(Integer id);

    ResponseDTO getById(Integer id);
}
