package com.Xoot.CreditoParaTi.repositories.pima;

import com.Xoot.CreditoParaTi.entity.pima.BranchOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IBranchOfficeDao extends JpaRepository<BranchOffice,Integer>, JpaSpecificationExecutor<BranchOffice> {
    @Query(nativeQuery = true, value = "select * from branch_offices where status_flag = 1")
    public List<BranchOffice> findAllActive();
}
