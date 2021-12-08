package com.Xoot.CreditoParaTi.repositories.pima;

import com.Xoot.CreditoParaTi.entity.pima.PimaTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPimaTransactionDao extends JpaRepository<PimaTransaction,Integer> {
    @Query(nativeQuery = true,value="select * from transaction where status_flag = 1")
    public List<PimaTransaction> findAllActive();

    @Query(nativeQuery = true,value="select * from transaction where user_id = :userId and status_flag = 1")
    public List<PimaTransaction> findByUser(@Param("userId") Integer userId);

    @Query(nativeQuery = true,value="select * from transaction where number_request = :creditId and transaction_type in :typeTransaction and status_flag = 1")
    public List<PimaTransaction> findByCreditID(@Param("creditId") Integer creditId, @Param("typeTransaction") List<Integer> typeTransaction);

    @Query(nativeQuery = true,value="select * from transaction where " +
            "number_request = :creditId and transaction_type = :typeTransaction and " +
            "status_flag = 1 order by crtd_on desc limit 1"
    )
    public PimaTransaction findValidate(@Param("creditId") Integer creditId, @Param("typeTransaction") Integer typeTransaction);
}
