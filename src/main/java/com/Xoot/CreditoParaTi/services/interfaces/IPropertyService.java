package com.Xoot.CreditoParaTi.services.interfaces;

import com.Xoot.CreditoParaTi.entity.Property;
import com.Xoot.CreditoParaTi.dto.PropertyDTO;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;

import java.util.List;

public interface IPropertyService {
    Property findById(Integer id);

    List<Property> findAllActive();

    ResponseDTO save(PropertyDTO propertyDTO);

    ResponseDTO update(Integer id, PropertyDTO propertyDTO);

    ResponseDTO active(Integer id);

    ResponseDTO delete(Integer id);
}
