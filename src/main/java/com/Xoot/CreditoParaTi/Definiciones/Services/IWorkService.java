package com.Xoot.CreditoParaTi.Definiciones.Services;

import com.Xoot.CreditoParaTi.entity.Work;
import com.Xoot.CreditoParaTi.entity.DTO.WorkDTO;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;

import java.util.List;

public interface IWorkService {
    Work findById(Integer id);

    List<Work> findAllActive();

    ResponseDTO save(WorkDTO additionalInformationDTO);

    ResponseDTO update(Integer id, WorkDTO additionalInformationDTO);

    ResponseDTO active(Integer id);

    ResponseDTO delete(Integer id);
}
