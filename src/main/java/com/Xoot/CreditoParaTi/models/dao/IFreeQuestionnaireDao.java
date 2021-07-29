package com.Xoot.CreditoParaTi.models.dao;

import com.Xoot.CreditoParaTi.entity.FreeQuestionnaire;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IFreeQuestionnaireDao extends CrudRepository<FreeQuestionnaire,Integer>, JpaSpecificationExecutor<FreeQuestionnaire> {
    @Query(nativeQuery = true, value = "select * from free_questionnaire where status_flag = 1")
    public List<FreeQuestionnaire> findAllActive();
}
