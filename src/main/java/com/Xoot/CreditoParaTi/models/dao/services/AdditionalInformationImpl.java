package com.Xoot.CreditoParaTi.models.dao.services;

import com.Xoot.CreditoParaTi.Definiciones.Services.IAdditionalInformationService;
import com.Xoot.CreditoParaTi.entity.AdditionalInformation;
import com.Xoot.CreditoParaTi.entity.DTO.AdditionalInformationDTO;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;
import com.Xoot.CreditoParaTi.models.dao.IAdditionalInformationDao;
import org.mapstruct.Mapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class AdditionalInformationImpl implements IAdditionalInformationService {
    @Autowired
    private IAdditionalInformationDao additionalInformationDao;



    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public AdditionalInformation findById(Integer id) { return additionalInformationDao.findById(id).orElse(null); }

    @Override
    @Transactional(readOnly = true)
    public List<AdditionalInformation> findAllActive() { return additionalInformationDao.findAllActive(); }

    @Override
    @Transactional
    public ResponseDTO save(AdditionalInformationDTO additionalInformationDTO) {
        AdditionalInformation additionalInformation = additionalInformationDao.save(modelMapper.map(additionalInformationDTO,AdditionalInformation.class));
        if (additionalInformation != null) {
            return  new ResponseDTO(additionalInformation,"Registro creado",true);
        }
        return  new ResponseDTO(additionalInformation,"Error en registro",false);
    }

    @Override
    public ResponseDTO update(Integer id, AdditionalInformationDTO additionalInformationDTO) {
        AdditionalInformation entity = additionalInformationDao.findById(id).orElse(null);
        if (entity != null) {
            additionalInformationDTO.setMdfd_on(new Date());
            /*ModelMapper mapper = new ModelMapper();*/
            modelMapper.getConfiguration().setSkipNullEnabled(true)
                    .setCollectionsMergeEnabled(false)
                    .setMatchingStrategy(MatchingStrategies.STRICT);
            modelMapper.map(additionalInformationDTO,entity);
            additionalInformationDao.save(entity);
            return  new ResponseDTO(entity,"Registro creado",true);
        }
        return  new ResponseDTO(additionalInformationDTO,"Error en registro",false);
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
