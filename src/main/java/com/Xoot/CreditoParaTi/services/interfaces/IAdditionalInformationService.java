package com.Xoot.CreditoParaTi.services.interfaces;

import com.Xoot.CreditoParaTi.entity.AdditionalInformation;
import com.Xoot.CreditoParaTi.dto.AdditionalInformationDTO;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;

import java.util.List;

public interface IAdditionalInformationService {
    AdditionalInformation findById(Integer id);

    List<AdditionalInformation> findAllActive();

    ResponseDTO save(AdditionalInformationDTO additionalInformationDTO);

    ResponseDTO update(Integer id, AdditionalInformationDTO additionalInformationDTO);

    ResponseDTO active(Integer id);

    ResponseDTO delete(Integer id);
}
