package com.Xoot.CreditoParaTi.repositories.interfaces;

import com.Xoot.CreditoParaTi.entity.AnswerQuestionnaire;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IAnswerQuestionnaireDao extends CrudRepository<AnswerQuestionnaire, Integer>, JpaSpecificationExecutor<AnswerQuestionnaire> {
    @Query(nativeQuery = true, value = "select * from answer_questionnaire where status_flag = 1")
    public List<AnswerQuestionnaire> findAllActive();

    @Query(nativeQuery = true, value = "select * from answer_questionnaire where medical_questionnaire_id = :medicalId order by answer_numer")
    public List<AnswerQuestionnaire> findByMedicalQuestionnaire(@Param("medicalId") Integer medicalId);

    @Query(nativeQuery = true, value = "SELECT * FROM answer_questionnaire WHERE creadit_application_id=:creditId order by answer_numer")
    public List<AnswerQuestionnaire> findByCreditId(@Param("creditId") Integer creditId);

    @Query(nativeQuery = true, value = "select * from answer_questionnaire " +
            "where medical_questionnaire_id = :medicalId and answer_numer = :answerNumber limit 1")
    public AnswerQuestionnaire findByMedicalQuestionnaireAnswer(@Param("medicalId") Integer medicalId
        ,@Param("answerNumber") Integer answerNumer);

}
