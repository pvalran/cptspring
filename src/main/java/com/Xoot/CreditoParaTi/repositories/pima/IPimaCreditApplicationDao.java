package com.Xoot.CreditoParaTi.repositories.pima;

import com.Xoot.CreditoParaTi.entity.pima.PimaCreditApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPimaCreditApplicationDao extends JpaRepository<PimaCreditApplication, Integer> {

	@Query(nativeQuery = true, value = "SELECT * FROM credits_aplications WHERE status_flag = 1")
	public List<PimaCreditApplication> findAllActive();
	
	@Query(nativeQuery = true, value = "SELECT * FROM credits_aplications WHERE id=:id AND status_flag = 1 LIMIT 1")
	public PimaCreditApplication getById(@Param("id") Integer id);
	
	@Query(nativeQuery = true, value = "SELECT * FROM credits_aplications WHERE user_id=:idUser AND status_flag = 1")
	public List<PimaCreditApplication> getAllByUser(@Param("idUser") Integer idUser);

	@Query(nativeQuery = true, value = "SELECT * FROM credits_aplications WHERE number_request=:creditId limit 1")
	public PimaCreditApplication FindByCreditUser(@Param("creditId") Integer creditId);

	@Query(nativeQuery = true, value = "SELECT ca.* FROM credits_aplications ca inner join users on users.id = ca.user_id  WHERE users.crtd_by = :employee")
	public List<PimaCreditApplication> FindByCreditEmployee(@Param("employee") String employee);

	@Query(nativeQuery = true, value = "select * from credits_aplications ca where DATE(crtd_on)  BETWEEN  ? and ?  and status_flag = 1")
	public List<PimaCreditApplication> getCreditTransDate(@Param("startDate") String startDate, @Param("endDate") String endDate);

	@Query(nativeQuery = true, value = "select * from credits_aplications ca inner join customers cu on ca.customer_id = cu.id where " +
			"convert(ca.number_request,char(11)) = :search or " +
			"cu.email = :search or " +
			"cu.curp = :search  or " +
			"concat(cu.name,' ',cu.paternal_last_name,' ',cu.mother_last_name) LIKE %:search%" +
			" and (ca.status_flag = 1 and cu.status_flag = 1)")
	public List<PimaCreditApplication> getCreditTransSearch(@Param("search") String search);
}
