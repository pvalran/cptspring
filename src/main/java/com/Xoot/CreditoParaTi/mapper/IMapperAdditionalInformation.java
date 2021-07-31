package com.Xoot.CreditoParaTi.mapper;

import com.Xoot.CreditoParaTi.entity.AdditionalInformation;
import com.Xoot.CreditoParaTi.entity.DTO.AdditionalInformationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IMapperAdditionalInformation {
    void UpdateAdditionalInformation(AdditionalInformationDTO dto, @MappingTarget AdditionalInformation entity);
}
