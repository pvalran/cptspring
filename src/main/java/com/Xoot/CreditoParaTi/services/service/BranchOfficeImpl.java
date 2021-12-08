package com.Xoot.CreditoParaTi.services.service;


import com.Xoot.CreditoParaTi.dto.BranchOfficeDTO;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;
import com.Xoot.CreditoParaTi.entity.pima.BranchOffice;
import com.Xoot.CreditoParaTi.repositories.pima.IBranchOfficeDao;
import com.Xoot.CreditoParaTi.services.interfaces.IBranchOfficeService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BranchOfficeImpl implements IBranchOfficeService {
    @Autowired
    private IBranchOfficeDao branchOfficeDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BranchOffice findById(Integer id) { return branchOfficeDao.findById(id).orElse(null); }

    @Override
    public List<BranchOffice> findAllActive() { return branchOfficeDao.findAllActive(); }

    @Override
    public ResponseDTO save(BranchOfficeDTO ObjDTO) {
        BranchOffice entity = branchOfficeDao.save(modelMapper.map(ObjDTO,BranchOffice.class));
        if (entity != null) {
            ObjDTO = modelMapper.map(entity, BranchOfficeDTO.class);
            return  new ResponseDTO(ObjDTO,"Registro creado",true);
        }
        return  new ResponseDTO(entity,"Error en registro",false);
    }

    @Override
    public ResponseDTO update(Integer id, BranchOfficeDTO ObjDTO) {
        BranchOffice entity = branchOfficeDao.findById(id).orElse(null);
        if (entity != null) {
            entity.setMdfd_on(new Date());
            modelMapper.getConfiguration().setSkipNullEnabled(true)
                    .setCollectionsMergeEnabled(false)
                    .setMatchingStrategy(MatchingStrategies.STRICT);
            modelMapper.map(ObjDTO,entity);
            branchOfficeDao.save(entity);
            ObjDTO = modelMapper.map(entity, BranchOfficeDTO.class);
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
        BranchOfficeDTO ObjDTO = new BranchOfficeDTO();
        BranchOffice entity = branchOfficeDao.findById(id).orElse(null);
        if (entity != null) {
            entity.setMdfd_on(new Date());
            entity.setStatus_flag(0);
            branchOfficeDao.save(entity);
            ObjDTO = modelMapper.map(entity, BranchOfficeDTO.class);
            return new ResponseDTO(ObjDTO,"Eliminación realizada con exito",true);
        }
        return new ResponseDTO(ObjDTO,"Error en la elimanación del registro",false);
    }
}

