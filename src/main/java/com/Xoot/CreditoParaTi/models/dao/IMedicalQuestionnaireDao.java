package com.Xoot.CreditoParaTi.models.dao;

import com.Xoot.CreditoParaTi.entity.MedicalQuestionnaire;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IMedicalQuestionnaireDao extends CrudRepository<MedicalQuestionnaire, Integer>, JpaSpecificationExecutor<MedicalQuestionnaire> {
    @Query(nativeQuery = true, value = "select * from medical_questionnaire where status_flag = 1")
    public List<MedicalQuestionnaire> findAllActive();
}
