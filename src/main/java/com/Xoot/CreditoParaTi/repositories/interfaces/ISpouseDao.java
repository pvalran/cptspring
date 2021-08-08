package com.Xoot.CreditoParaTi.repositories.interfaces;

import com.Xoot.CreditoParaTi.entity.Spouse;
import com.Xoot.CreditoParaTi.entity.Work;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISpouseDao extends CrudRepository<Spouse,Integer>, JpaSpecificationExecutor<Spouse> {
    @Query(nativeQuery = true,value="select * from spouse where status_flag = 1")
    public List<Spouse> findAllActive();

    @Query(nativeQuery = true, value = "SELECT * FROM spouse WHERE creadit_application_id=:creditId and status_flag = 1 LIMIT 1")
    public Spouse findByCreditId(@Param("creditId") Integer creditId);
}
