package com.Xoot.CreditoParaTi.services.interfaces;

import com.Xoot.CreditoParaTi.entity.app.Work;
import com.Xoot.CreditoParaTi.dto.WorkDTO;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;

import java.util.List;

public interface IWorkService {
    Work findById(Integer id);

    List<Work> findAllActive();

    ResponseDTO save(WorkDTO additionalInformationDTO);

    ResponseDTO update(Integer id, WorkDTO additionalInformationDTO);

    ResponseDTO active(Integer id);

    ResponseDTO delete(Integer id);
}
