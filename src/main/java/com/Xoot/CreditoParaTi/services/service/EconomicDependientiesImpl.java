package com.Xoot.CreditoParaTi.services.service;

import com.Xoot.CreditoParaTi.services.interfaces.IEconomicDependientiesService;
import com.Xoot.CreditoParaTi.dto.EconomicDependientiesDto;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;
import com.Xoot.CreditoParaTi.entity.EconomicDependents;
import com.Xoot.CreditoParaTi.repositories.interfaces.IEconomicDependientiesDAO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
        EconomicDependents entity = economicDependientiesDAO.findById(id).orElse(null);
        if (entity != null) {
            entity.setMdfd_on(new Date());
            modelMapper.getConfiguration().setSkipNullEnabled(true)
                    .setCollectionsMergeEnabled(false)
                    .setMatchingStrategy(MatchingStrategies.STRICT);
            modelMapper.map(economicDependientiesDto,entity);
            economicDependientiesDAO.save(entity);
            economicDependientiesDto = modelMapper.map(entity, EconomicDependientiesDto.class);
            return new ResponseDTO(economicDependientiesDto,"Modificaciòn realizada con exito",true);
        }
        return  new ResponseDTO(economicDependientiesDto,"Error en la modificación del registro",false);
    }

    @Override
    public ResponseDTO active(Integer id) {
        return null;
    }

    @Override
    public ResponseDTO delete(Integer id) {
        try {
            economicDependientiesDAO.deleteById(id);
            return new ResponseDTO("","Eliminacion realizada con exito",true);
        } catch (Exception ex) {
            return new ResponseDTO("", "Error en la Eliminacion del registro", false);
        }
    }
}
