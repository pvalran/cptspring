package com.Xoot.CreditoParaTi.services.interfaces;

import com.Xoot.CreditoParaTi.dto.TypeDocumentDTO;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;
import com.Xoot.CreditoParaTi.entity.TypeDocument;

import java.util.List;

public interface ITypeDocumentService {
    TypeDocument findById(Integer id);

    List<TypeDocument> findAllActive();

    List<TypeDocument> findByDescription(String Description);

    ResponseDTO save(TypeDocumentDTO catalogo);

    ResponseDTO update(Integer id, TypeDocumentDTO catalogo);

    ResponseDTO active(Integer id);

    ResponseDTO delete(Integer id);

    ResponseDTO getById(Integer id);
}
