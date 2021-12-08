package com.Xoot.CreditoParaTi.services.interfaces;

import com.Xoot.CreditoParaTi.entity.app.Spouse;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;
import com.Xoot.CreditoParaTi.dto.SpouseDTO;

import java.util.List;

public interface ISpouseService {
    Spouse findById(Integer id);

    List<Spouse> findAllActive();

    ResponseDTO save(SpouseDTO spouseDTO);

    ResponseDTO update(Integer id, SpouseDTO spouseDTO );

    ResponseDTO active(Integer id);

    ResponseDTO delete(Integer id);
}
