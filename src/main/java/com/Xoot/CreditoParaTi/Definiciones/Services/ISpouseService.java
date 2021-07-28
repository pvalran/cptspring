package com.Xoot.CreditoParaTi.Definiciones.Services;

import com.Xoot.CreditoParaTi.entity.Spouse;
import com.Xoot.CreditoParaTi.entity.DTO.AdditionalInformationDTO;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;
import com.Xoot.CreditoParaTi.entity.DTO.SpouseDTO;

import java.util.List;

public interface ISpouseService {
    Spouse findById(Integer id);

    List<Spouse> findAllActive();

    ResponseDTO save(SpouseDTO spouseDTO);

    ResponseDTO update(Integer id, SpouseDTO spouseDTO );

    ResponseDTO active(Integer id);

    ResponseDTO delete(Integer id);
}
