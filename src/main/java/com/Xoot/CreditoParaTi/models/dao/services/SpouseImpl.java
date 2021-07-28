package com.Xoot.CreditoParaTi.models.dao.services;

import com.Xoot.CreditoParaTi.Definiciones.Services.ISpouseService;
import com.Xoot.CreditoParaTi.entity.Spouse;
import com.Xoot.CreditoParaTi.entity.DTO.SpouseDTO;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;
import com.Xoot.CreditoParaTi.models.dao.ISpouseDao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
