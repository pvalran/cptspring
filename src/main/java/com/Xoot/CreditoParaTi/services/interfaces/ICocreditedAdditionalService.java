package com.Xoot.CreditoParaTi.services.interfaces;

import com.Xoot.CreditoParaTi.dto.CocreditedAdditionalDTO;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;
import com.Xoot.CreditoParaTi.entity.app.CocreditedAdditional;

import java.util.List;

public interface ICocreditedAdditionalService {
    CocreditedAdditional findById(Integer id);

    List<CocreditedAdditional> findAllActive();

    ResponseDTO save(CocreditedAdditionalDTO ObjDTO);

    ResponseDTO update(Integer id, CocreditedAdditionalDTO ObjDTO);

    ResponseDTO active(Integer id);

    ResponseDTO delete(Integer id);
}
