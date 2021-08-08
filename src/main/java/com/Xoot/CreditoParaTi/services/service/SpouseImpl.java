package com.Xoot.CreditoParaTi.services.service;

import com.Xoot.CreditoParaTi.services.interfaces.ISpouseService;
import com.Xoot.CreditoParaTi.entity.Spouse;
import com.Xoot.CreditoParaTi.dto.SpouseDTO;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;
import com.Xoot.CreditoParaTi.repositories.interfaces.ISpouseDao;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class SpouseImpl implements ISpouseService {
    @Autowired
    private ISpouseDao spouseDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public Spouse findById(Integer id) { return spouseDao.findById(id).orElse(null); }

    @Override
    @Transactional(readOnly = true)
    public List<Spouse> findAllActive() { return spouseDao.findAllActive(); }

    @Override
    @Transactional
    public ResponseDTO save(SpouseDTO spouseDTO) {
        Spouse spouse = spouseDao.save(modelMapper.map(spouseDTO,Spouse.class));
        if (spouse != null) {
            return  new ResponseDTO(spouse,"Registro creado",true);
        }
        return  new ResponseDTO(spouse,"Error en registro",false);
    }

    @Override
    public ResponseDTO update(Integer id, SpouseDTO spouseDTO) {
        Spouse entity = spouseDao.findById(id).orElse(null);
        if (entity != null) {
            entity.setMdfd_on(new Date());
            modelMapper.getConfiguration().setSkipNullEnabled(true)
                    .setCollectionsMergeEnabled(false)
                    .setMatchingStrategy(MatchingStrategies.STRICT);
            modelMapper.map(spouseDTO,entity);
            spouseDao.save(entity);
            spouseDTO = modelMapper.map(entity,SpouseDTO.class);
            return new ResponseDTO(spouseDTO,"Modificaciòn realizada con exito",true);
        }
        return new ResponseDTO(spouseDTO,"Error en la modificación del registro",false);
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
