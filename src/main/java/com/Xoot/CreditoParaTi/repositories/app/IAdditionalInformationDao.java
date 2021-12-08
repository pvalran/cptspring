package com.Xoot.CreditoParaTi.repositories.app;

import com.Xoot.CreditoParaTi.entity.app.AdditionalInformation;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IAdditionalInformationDao extends CrudRepository<AdditionalInformation, Integer>, JpaSpecificationExecutor<AdditionalInformation> {
    @Query(nativeQuery = true, value = "select * from additional_information where status_flag = 1")
    public List<AdditionalInformation> findAllActive();

    @Query(nativeQuery = true, value = "SELECT * FROM additional_information WHERE number_request=:creditId and status_flag = 1 order by crtd_on desc limit 1")
    public AdditionalInformation findByCreditId(@Param("creditId") Integer creditId);
}
