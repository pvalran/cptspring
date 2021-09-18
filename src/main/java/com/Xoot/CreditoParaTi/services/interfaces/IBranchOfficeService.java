package com.Xoot.CreditoParaTi.services.interfaces;

import com.Xoot.CreditoParaTi.dto.BranchOfficeDTO;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;
import com.Xoot.CreditoParaTi.entity.BranchOffice;

import java.util.List;

public interface IBranchOfficeService {
    BranchOffice findById(Integer id);

    List<BranchOffice> findAllActive();

    ResponseDTO save(BranchOfficeDTO ObjDTO);

    ResponseDTO update(Integer id, BranchOfficeDTO ObjDTO);

    ResponseDTO active(Integer id);

    ResponseDTO delete(Integer id);
}
