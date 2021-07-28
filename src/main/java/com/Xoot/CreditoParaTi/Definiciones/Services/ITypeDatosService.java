package com.Xoot.CreditoParaTi.Definiciones.Services;

import com.Xoot.CreditoParaTi.entity.DTO.TypeDatosDTO;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;
import com.Xoot.CreditoParaTi.entity.TypeDatos;

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
