package com.Xoot.CreditoParaTi.Definiciones.Services;

import com.Xoot.CreditoParaTi.entity.Property;
import com.Xoot.CreditoParaTi.entity.DTO.PropertyDTO;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;

import java.util.List;

public interface IPropertyService {
    Property findById(Integer id);

    List<Property> findAllActive();

    ResponseDTO save(PropertyDTO propertyDTO);

    ResponseDTO update(Integer id, PropertyDTO propertyDTO);

    ResponseDTO active(Integer id);

    ResponseDTO delete(Integer id);
}
