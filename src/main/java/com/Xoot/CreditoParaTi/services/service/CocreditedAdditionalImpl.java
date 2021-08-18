package com.Xoot.CreditoParaTi.services.service;

import com.Xoot.CreditoParaTi.dto.CocreditedAdditionalDTO;

import com.Xoot.CreditoParaTi.dto.ResponseDTO;
import com.Xoot.CreditoParaTi.entity.CocreditedAdditional;

import com.Xoot.CreditoParaTi.repositories.interfaces.ICocreditedAdditionalDao;
import com.Xoot.CreditoParaTi.services.interfaces.ICocreditedAdditionalService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CocreditedAdditionalImpl implements ICocreditedAdditionalService {
    @Autowired
    private ICocreditedAdditionalDao cocreditedAdditionalDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CocreditedAdditional findById(Integer id) {
        return cocreditedAdditionalDao.findById(id).orElse(null);
    }

    @Override
    public List<CocreditedAdditional> findAllActive() {
        return cocreditedAdditionalDao.findAllActive();
    }

    @Override
    public ResponseDTO save(CocreditedAdditionalDTO ObjDTO) {
        modelMapper.getConfiguration().setSkipNullEnabled(true)
                .setCollectionsMergeEnabled(false)
                .setMatchingStrategy(MatchingStrategies.STRICT);
        CocreditedAdditional entity = cocreditedAdditionalDao.save(modelMapper.map(ObjDTO,CocreditedAdditional.class));
        if (entity != null) {
            ObjDTO = modelMapper.map(entity, CocreditedAdditionalDTO.class);
            return  new ResponseDTO(ObjDTO,"Registro creado",true);
        }
        return  new ResponseDTO(ObjDTO,"Error en registro",false);
    }

    @Override
    public ResponseDTO update(Integer id, CocreditedAdditionalDTO ObjDTO) {
        CocreditedAdditional entity = cocreditedAdditionalDao.findById(id).orElse(null);
        if (entity != null) {
            entity.setMdfd_on(new Date());
            modelMapper.getConfiguration().setSkipNullEnabled(true)
                    .setCollectionsMergeEnabled(false)
                    .setMatchingStrategy(MatchingStrategies.STRICT);
            modelMapper.map(ObjDTO,entity);
            cocreditedAdditionalDao.save(entity);
            ObjDTO = modelMapper.map(entity, CocreditedAdditionalDTO.class);
            return new ResponseDTO(ObjDTO,"Modificaciòn realizada con exito",true);
        }
        return  new ResponseDTO(ObjDTO,"Error en la modificación del registro",false);
    }

    @Override
    public ResponseDTO active(Integer id) {
        return null;
    }

    @Override
    public ResponseDTO delete(Integer id) {
        try {
            cocreditedAdditionalDao.deleteById(id);
            return new ResponseDTO("","Eliminacion realizada con exito",true);
        } catch (Exception ex) {
            return new ResponseDTO("", "Error en la Eliminacion del registro", false);
        }
    }
}
