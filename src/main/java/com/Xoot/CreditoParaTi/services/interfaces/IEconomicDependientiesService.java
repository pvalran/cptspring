package com.Xoot.CreditoParaTi.services.interfaces;


import com.Xoot.CreditoParaTi.dto.EconomicDependientiesDto;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;
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
