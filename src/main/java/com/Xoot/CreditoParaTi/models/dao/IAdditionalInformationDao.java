package com.Xoot.CreditoParaTi.models.dao;

import com.Xoot.CreditoParaTi.entity.AdditionalInformation;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IAdditionalInformationDao extends CrudRepository<AdditionalInformation, Integer>, JpaSpecificationExecutor<AdditionalInformation> {
    @Query(nativeQuery = true, value = "select * from additional_information where status_flag = 1")
    public List<AdditionalInformation> findAllActive();
}
