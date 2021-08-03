package com.Xoot.CreditoParaTi.models.dao.services;

import com.Xoot.CreditoParaTi.Definiciones.Services.IPropertyService;
import com.Xoot.CreditoParaTi.entity.Property;
import com.Xoot.CreditoParaTi.entity.DTO.PropertyDTO;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;
import com.Xoot.CreditoParaTi.entity.Reference;
import com.Xoot.CreditoParaTi.models.dao.IPropertyDao;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class PropertyImpl implements IPropertyService {
    @Autowired
    private IPropertyDao propertyDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public Property findById(Integer id) { return propertyDao.findById(id).orElse(null); }

    @Override
    @Transactional(readOnly = true)
    public List<Property> findAllActive() { return propertyDao.findAllActive(); }

    @Override
    @Transactional
    public ResponseDTO save(PropertyDTO PropertyDTO) {
        Property Property = propertyDao.save(modelMapper.map(PropertyDTO,Property.class));
        if (Property != null) {
            return  new ResponseDTO(Property,"Registro creado",true);
        }
        return  new ResponseDTO(Property,"Error en registro",false);
    }

    @Override
    public ResponseDTO update(Integer id, PropertyDTO ObjDTO) {
        Property Entity = propertyDao.findById(id).orElse(null);
        if (Entity != null) {
            Entity.setMdfd_on(new Date());
            modelMapper.getConfiguration().setSkipNullEnabled(true)
                    .setCollectionsMergeEnabled(false)
                    .setMatchingStrategy(MatchingStrategies.STRICT);
            modelMapper.map(ObjDTO,Entity);
            propertyDao.save(Entity);
            return new ResponseDTO(ObjDTO,"Modificaciòn realizada con exito",true);
        }
        return new ResponseDTO(ObjDTO,"Error en la modificación del registro",false);
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
