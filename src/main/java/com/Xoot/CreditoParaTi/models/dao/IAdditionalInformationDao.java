package com.Xoot.CreditoParaTi.models.dao;

import com.Xoot.CreditoParaTi.entity.AdditionalInformation;
import com.Xoot.CreditoParaTi.entity.Reference;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IAdditionalInformationDao extends CrudRepository<AdditionalInformation, Integer>, JpaSpecificationExecutor<AdditionalInformation> {
    @Query(nativeQuery = true, value = "select * from additional_information where status_flag = 1")
    public List<AdditionalInformation> findAllActive();

    @Query(nativeQuery = true, value = "SELECT * FROM additional_information WHERE creadit_application_id=:creditId")
    public AdditionalInformation findByCreditId(@Param("creditId") Integer creditId);

}
