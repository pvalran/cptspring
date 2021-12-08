package com.Xoot.CreditoParaTi.services.service;

import com.Xoot.CreditoParaTi.services.interfaces.IWorkService;
import com.Xoot.CreditoParaTi.entity.app.Work;
import com.Xoot.CreditoParaTi.dto.WorkDTO;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;
import com.Xoot.CreditoParaTi.repositories.app.IWorkDao;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class WorkImpl implements IWorkService {
    @Autowired
    private IWorkDao WorkDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public Work findById(Integer id) { return WorkDao.findById(id).orElse(null); }

    @Override
    @Transactional(readOnly = true)
    public List<Work> findAllActive() { return WorkDao.findAllActive(); }

    @Override
    @Transactional
    public ResponseDTO save(WorkDTO WorkDTO) {
        Work Work = WorkDao.save(modelMapper.map(WorkDTO,Work.class));
        if (Work != null) {
            return  new ResponseDTO(Work,"Registro creado",true);
        }
        return  new ResponseDTO(Work,"Error en registro",false);
    }

    @Override
    public ResponseDTO update(Integer id, WorkDTO ObjDTO) {
        Work Entity = WorkDao.findById(id).orElse(null);
        if (Entity != null) {
            Entity.setMdfd_on(new Date());
            modelMapper.getConfiguration().setSkipNullEnabled(true)
                    .setCollectionsMergeEnabled(false)
                    .setMatchingStrategy(MatchingStrategies.STRICT);
            modelMapper.map(ObjDTO,Entity);
            WorkDao.save(Entity);
            ObjDTO = modelMapper.map(Entity,WorkDTO.class);
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
