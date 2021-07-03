package com.Xoot.CreditoParaTi.models.dao.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Xoot.CreditoParaTi.Definiciones.Services.ILocationCityService;
import com.Xoot.CreditoParaTi.entity.LocationCity;
import com.Xoot.CreditoParaTi.models.dao.ILocationCityDao;

@Service
public class LocationCityImpl implements ILocationCityService {

	@Autowired
	private ILocationCityDao locationCitydao;
	
	@Override
	public LocationCity save(LocationCity locationCity) {
		return locationCitydao.save(locationCity);
	}
	
	@Override
	public List<LocationCity> findByListName(List<String> lstNameCity) {
		
		return locationCitydao.findByListName(lstNameCity);
	}
}
