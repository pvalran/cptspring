package com.Xoot.CreditoParaTi.models.dao;

import com.Xoot.CreditoParaTi.entity.AnswerQuestionnaire;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IAnswerQuestionnaireDao extends CrudRepository<AnswerQuestionnaire, Integer>, JpaSpecificationExecutor<AnswerQuestionnaire> {
    @Query(nativeQuery = true, value = "select * from answer_questionnaire where status_flag = 1")
    public List<AnswerQuestionnaire> findAllActive();

    @Query(nativeQuery = true, value = "select * from answer_questionnaire where medical_questionnaire_id = :medicalId")
    public List<AnswerQuestionnaire> findByMedicalQuestionnaire(@Param("medicalId") Integer medicalId);

}
