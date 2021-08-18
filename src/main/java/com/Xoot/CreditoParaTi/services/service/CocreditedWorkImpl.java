package com.Xoot.CreditoParaTi.services.service;

import com.Xoot.CreditoParaTi.dto.CocreditedWorkDTO;

import com.Xoot.CreditoParaTi.dto.ResponseDTO;
import com.Xoot.CreditoParaTi.entity.CocreditedWork;

import com.Xoot.CreditoParaTi.repositories.interfaces.ICocreditedWorkDao;
import com.Xoot.CreditoParaTi.services.interfaces.ICocreditedWorkService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CocreditedWorkImpl implements ICocreditedWorkService {
    @Autowired
    private ICocreditedWorkDao cocreditedWorkDao;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CocreditedWork findById(Integer id) {
        return null;
    }

    @Override
    public List<CocreditedWork> findAllActive() {
        return null;
    }

    @Override
    public ResponseDTO save(CocreditedWorkDTO ObjDTO) {
        CocreditedWork entity = cocreditedWorkDao.save(modelMapper.map(ObjDTO,CocreditedWork.class));
        if (entity != null) {
            ObjDTO = modelMapper.map(entity, CocreditedWorkDTO.class);
            return  new ResponseDTO(ObjDTO,"Registro creado",true);
        }
        return  new ResponseDTO(ObjDTO,"Error en registro",false);
    }

    @Override
    public ResponseDTO update(Integer id, CocreditedWorkDTO ObjDTO) {
        CocreditedWork entity = cocreditedWorkDao.findById(id).orElse(null);
        if (entity != null) {
            entity.setMdfd_on(new Date());
            modelMapper.getConfiguration().setSkipNullEnabled(true)
                    .setCollectionsMergeEnabled(false)
                    .setMatchingStrategy(MatchingStrategies.STRICT);
            modelMapper.map(ObjDTO,entity);
            cocreditedWorkDao.save(entity);
            ObjDTO = modelMapper.map(entity, CocreditedWorkDTO.class);
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
            cocreditedWorkDao.deleteById(id);
            return new ResponseDTO("","Eliminacion realizada con exito",true);
        } catch (Exception ex) {
            return new ResponseDTO("", "Error en la Eliminacion del registro", false);
        }
    }
}
