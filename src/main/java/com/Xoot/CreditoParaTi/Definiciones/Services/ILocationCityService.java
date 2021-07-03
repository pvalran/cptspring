package com.Xoot.CreditoParaTi.Definiciones.Services;

import java.util.List;

import com.Xoot.CreditoParaTi.entity.LocationCity;

public interface ILocationCityService {
		
	public LocationCity save(LocationCity locationCity);

	List<LocationCity> findByListName(List<String> lstNameCity);
}
