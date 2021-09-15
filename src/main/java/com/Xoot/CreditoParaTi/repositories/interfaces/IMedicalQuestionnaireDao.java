package com.Xoot.CreditoParaTi.repositories.interfaces;

import com.Xoot.CreditoParaTi.entity.MedicalQuestionnaire;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IMedicalQuestionnaireDao extends CrudRepository<MedicalQuestionnaire, Integer>, JpaSpecificationExecutor<MedicalQuestionnaire> {
    @Query(nativeQuery = true, value = "select * from medical_questionnaire where status_flag = 1")
    public List<MedicalQuestionnaire> findAllActive();

    @Query(nativeQuery = true, value = "select * from medical_questionnaire where number_request = :creditID limit 1")
    public MedicalQuestionnaire findByCreditID(@Param("creditID") Integer creditID);
}
