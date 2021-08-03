package com.Xoot.CreditoParaTi.models.dao;

import com.Xoot.CreditoParaTi.entity.AnswerQuestionnaire;
import com.Xoot.CreditoParaTi.entity.FreeQuestionnaire;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IFreeQuestionnaireDao extends CrudRepository<FreeQuestionnaire,Integer>, JpaSpecificationExecutor<FreeQuestionnaire> {
    @Query(nativeQuery = true, value = "select * from free_questionnaire where status_flag = 1")
    public List<FreeQuestionnaire> findAllActive();

    @Query(nativeQuery = true, value = "select * from free_questionnaire where medical_questionnaire_id = :medicalId")
    public List<FreeQuestionnaire> findByMedicalQuestionnaire(@Param("medicalId") Integer medicalId);

}
