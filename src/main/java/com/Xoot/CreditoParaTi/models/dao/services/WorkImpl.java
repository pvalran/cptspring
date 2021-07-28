package com.Xoot.CreditoParaTi.models.dao.services;

import com.Xoot.CreditoParaTi.Definiciones.Services.IWorkService;
import com.Xoot.CreditoParaTi.entity.Work;
import com.Xoot.CreditoParaTi.entity.DTO.WorkDTO;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;
import com.Xoot.CreditoParaTi.models.dao.IWorkDao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WorkImpl implements IWorkService {
    @Autowired
    private IWorkDao WorkDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public Work findById(Integer id) { return WorkDao.findById(id).orElse(null); }

    @Override
    @Transactional(readOnly = true)
    public List<Work> findAllActive() { return WorkDao.findAllActive(); }

    @Override
    @Transactional
    public ResponseDTO save(WorkDTO WorkDTO) {
        Work Work = WorkDao.save(modelMapper.map(WorkDTO,Work.class));
        if (Work != null) {
            return  new ResponseDTO(Work,"Registro creado",true);
        }
        return  new ResponseDTO(Work,"Error en registro",false);
    }

    @Override
    public ResponseDTO update(Integer id, WorkDTO additionalInformationDTO) {
        return null;
    }

    @Override
    public ResponseDTO active(Integer id) {
        return null;
    }

    @Override
    public ResponseDTO delete(Integer id) {
        return null;
    }
}
