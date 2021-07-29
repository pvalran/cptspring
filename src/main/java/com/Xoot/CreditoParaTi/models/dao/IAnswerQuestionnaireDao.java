package com.Xoot.CreditoParaTi.models.dao;

import com.Xoot.CreditoParaTi.entity.AnswerQuestionnaire;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IAnswerQuestionnaireDao extends CrudRepository<AnswerQuestionnaire, Integer>, JpaSpecificationExecutor<AnswerQuestionnaire> {
    @Query(nativeQuery = true, value = "select * from answer_questionnaire where status_flag = 1")
    public List<AnswerQuestionnaire> findAllActive();
}
