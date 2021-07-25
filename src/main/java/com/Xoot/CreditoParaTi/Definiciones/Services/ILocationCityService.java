package com.Xoot.CreditoParaTi.Definiciones.Services;

import java.util.List;

import com.Xoot.CreditoParaTi.entity.LocationCity;
import com.Xoot.CreditoParaTi.entity.LocationState;

public interface ILocationCityService {
		
	public LocationCity save(LocationCity locationCity);

	List<LocationCity> findAllActive();

	List<LocationCity> getMuntoState(Integer id);

	List<LocationCity> findByListName(List<String> lstNameCity);
}
