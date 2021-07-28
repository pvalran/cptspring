package com.Xoot.CreditoParaTi.Definiciones.Services;


import com.Xoot.CreditoParaTi.entity.DTO.EconomicDependientiesDto;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;
import com.Xoot.CreditoParaTi.entity.EconomicDependents;

import java.util.List;

public interface IEconomicDependientiesService {
    EconomicDependents findById(Integer id);

    List<EconomicDependents> findAllActive();

    ResponseDTO save(EconomicDependientiesDto economicDependientiesDto);

    ResponseDTO update(Integer id, EconomicDependientiesDto economicDependientiesDto);

    ResponseDTO active(Integer id);

    ResponseDTO delete(Integer id);
}
