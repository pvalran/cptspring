package com.Xoot.CreditoParaTi.models.dao.services;

import com.Xoot.CreditoParaTi.Definiciones.Services.IEconomicDependientiesService;
import com.Xoot.CreditoParaTi.entity.DTO.EconomicDependientiesDto;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;
import com.Xoot.CreditoParaTi.entity.EconomicDependents;
import com.Xoot.CreditoParaTi.models.dao.IEconomicDependientiesDAO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EconomicDependientiesImpl implements IEconomicDependientiesService {
    @Autowired
    private IEconomicDependientiesDAO economicDependientiesDAO;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public EconomicDependents findById(Integer id) {
        return economicDependientiesDAO.findById(id).orElse(null);
    }

    @Override
    public List<EconomicDependents> findAllActive() {
        return economicDependientiesDAO.findAllActive();
    }

    @Override
    public ResponseDTO save(EconomicDependientiesDto economicDependientiesDto) {
        EconomicDependents economicDependents;
        economicDependents = economicDependientiesDAO.save(modelMapper.map(economicDependientiesDto,EconomicDependents.class));
        if (economicDependents != null) {
            return  new ResponseDTO(economicDependents,"Registro creado",true);
        }
        return  new ResponseDTO(economicDependents,"Error en registro",false);
    }

    @Override
    public ResponseDTO update(Integer id, EconomicDependientiesDto economicDependientiesDto) {
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
