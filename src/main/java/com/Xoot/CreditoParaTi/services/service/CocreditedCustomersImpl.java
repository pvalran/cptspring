package com.Xoot.CreditoParaTi.services.service;

import com.Xoot.CreditoParaTi.dto.CocreditedCustomersDTO;

import com.Xoot.CreditoParaTi.dto.ResponseDTO;
import com.Xoot.CreditoParaTi.entity.CocreditedCustomers;

import com.Xoot.CreditoParaTi.repositories.interfaces.ICocreditedCustomersDao;
import com.Xoot.CreditoParaTi.services.interfaces.ICocreditedCustomersService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CocreditedCustomersImpl implements ICocreditedCustomersService {
    @Autowired
    private ICocreditedCustomersDao cocreditedCustomersDao;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CocreditedCustomers findById(Integer id) {
        return null;
    }

    @Override
    public List<CocreditedCustomers> findAllActive() {
        return null;
    }

    @Override
    public ResponseDTO save(CocreditedCustomersDTO ObjDTO) {
        CocreditedCustomers entity = cocreditedCustomersDao.save(modelMapper.map(ObjDTO,CocreditedCustomers.class));
        if (entity != null) {
            ObjDTO = modelMapper.map(entity, CocreditedCustomersDTO.class);
            return  new ResponseDTO(ObjDTO,"Registro creado",true);
        }
        return  new ResponseDTO(ObjDTO,"Error en registro",false);
    }

    @Override
    public ResponseDTO update(Integer id, CocreditedCustomersDTO ObjDTO) {
        CocreditedCustomers entity = cocreditedCustomersDao.findById(id).orElse(null);
        if (entity != null) {
            entity.setMdfd_on(new Date());
            modelMapper.getConfiguration().setSkipNullEnabled(true)
                    .setCollectionsMergeEnabled(false)
                    .setMatchingStrategy(MatchingStrategies.STRICT);
            modelMapper.map(ObjDTO,entity);
            cocreditedCustomersDao.save(entity);
            ObjDTO = modelMapper.map(entity, CocreditedCustomersDTO.class);
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
            cocreditedCustomersDao.deleteById(id);
            return new ResponseDTO("","Eliminacion realizada con exito",true);
        } catch (Exception ex) {
            return new ResponseDTO("", "Error en la Eliminacion del registro", false);
        }
    }
}
