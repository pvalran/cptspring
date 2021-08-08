package com.Xoot.CreditoParaTi.services.service;

import com.Xoot.CreditoParaTi.services.interfaces.ITransactionService;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;
import com.Xoot.CreditoParaTi.dto.TransactionDTO;
import com.Xoot.CreditoParaTi.entity.transaction;
import com.Xoot.CreditoParaTi.repositories.interfaces.ITransactionDao;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransactionImpl implements ITransactionService {
    @Autowired
    ITransactionDao transactionDao;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public transaction findById(Integer id) {
        return transactionDao.findById(id).orElse(null);
    }

    @Override
    public List<transaction> findAllActive() {
        return transactionDao.findAllActive();
    }

    @Override
    public List<transaction> findByUser(Integer userId) {
        return transactionDao.findByUser(userId);
    }

    @Override
    public ResponseDTO save(TransactionDTO transactionDTO) {
        transaction ObjTrans = transactionDao.save(modelMapper.map(transactionDTO,transaction.class));
        if (ObjTrans != null) {
            return  new ResponseDTO(ObjTrans,"Registro creado",true);
        }
        return  new ResponseDTO(ObjTrans,"Error en registro",false);
    }

    @Override
    public ResponseDTO update(Integer id, TransactionDTO transactionDTO) {
        transaction entity = transactionDao.findById(id).orElse(null);
        if (entity != null) {
            entity.setMdfd_on(new Date());
            modelMapper.getConfiguration().setSkipNullEnabled(true)
                    .setCollectionsMergeEnabled(false)
                    .setMatchingStrategy(MatchingStrategies.STRICT);
            modelMapper.map(transactionDTO,entity);
            transactionDao.save(entity);
            transactionDTO = modelMapper.map(entity, TransactionDTO.class);
            return new ResponseDTO(transactionDTO,"Modificaciòn realizada con exito",true);
        }
        return new ResponseDTO(transactionDTO,"Error en la modificación del registro",false);
    }

    @Override
    public ResponseDTO active(Integer id) {
        return null;
    }

    @Override
    public ResponseDTO delete(Integer id) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transaction entity = transactionDao.findById(id).orElse(null);
        if (entity != null) {
            entity.setMdfd_on(new Date());
            entity.setStatus_flag(0);
            transactionDao.save(entity);
            transactionDTO = modelMapper.map(entity, TransactionDTO.class);
            return new ResponseDTO(transactionDTO,"Eliminación realizada con exito",true);
        }
        return new ResponseDTO(transactionDTO,"Error en la elimanación del registro",false);
    }
}
