package com.Xoot.CreditoParaTi.Definiciones.Services;

import com.Xoot.CreditoParaTi.entity.AdditionalInformation;
import com.Xoot.CreditoParaTi.entity.DTO.AdditionalInformationDTO;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;

import java.util.List;

public interface IAdditionalInformationService {
    AdditionalInformation findById(Integer id);

    List<AdditionalInformation> findAllActive();

    ResponseDTO save(AdditionalInformationDTO additionalInformationDTO);

    ResponseDTO update(Integer id, AdditionalInformationDTO additionalInformationDTO);

    ResponseDTO active(Integer id);

    ResponseDTO delete(Integer id);
}
