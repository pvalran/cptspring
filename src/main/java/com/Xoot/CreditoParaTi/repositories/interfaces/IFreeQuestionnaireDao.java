package com.Xoot.CreditoParaTi.repositories.interfaces;

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

    @Query(nativeQuery = true, value = "select * from free_questionnaire where medical_questionnaire_id = :medicalId  order by answer_numer")
    public List<FreeQuestionnaire> findByMedicalQuestionnaire(@Param("medicalId") Integer medicalId);

    @Query(nativeQuery = true, value = "SELECT * FROM free_questionnaire WHERE creadit_application_id=:creditId order by answer_numer")
    public List<FreeQuestionnaire> findByCreditId(@Param("creditId") Integer creditId);

    @Query(nativeQuery = true, value = "select * from free_questionnaire " +
            "where medical_questionnaire_id = :medicalId and answer_numer = :answerNumber limit 1")
    public FreeQuestionnaire findByMedicalQuestionnaireAnswer(@Param("medicalId") Integer medicalId
            ,@Param("answerNumber") Integer answerNumer);
}
