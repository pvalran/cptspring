package com.Xoot.CreditoParaTi.services.interfaces;

import java.util.List;

import com.Xoot.CreditoParaTi.dto.LocationsCitiesDTO;
import com.Xoot.CreditoParaTi.entity.LocationCity;

public interface ILocationCityService {
		
	public LocationCity save(LocationCity locationCity);

	List<LocationCity> findAllActive();

	List<LocationsCitiesDTO> getMuntoState(Integer id);

	List<LocationCity> findByListName(List<String> lstNameCity);
}
