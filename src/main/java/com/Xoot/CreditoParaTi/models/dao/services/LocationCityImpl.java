package com.Xoot.CreditoParaTi.models.dao.services;

import java.util.List;

import com.Xoot.CreditoParaTi.entity.LocationState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Xoot.CreditoParaTi.Definiciones.Services.ILocationCityService;
import com.Xoot.CreditoParaTi.entity.LocationCity;
import com.Xoot.CreditoParaTi.models.dao.ILocationCityDao;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LocationCityImpl implements ILocationCityService {

	@Autowired
	private ILocationCityDao locationCitydao;
	
	@Override
	public LocationCity save(LocationCity locationCity) {
		return locationCitydao.save(locationCity);
	}

	@Override
	@Transactional(readOnly = true)
	public List<LocationCity> findAllActive() { return locationCitydao.findAll(); }

	@Override
	@Transactional(readOnly = true)
	public List<LocationCity> getMuntoState(Integer id) { return locationCitydao.getMuntoState(id); }

	@Override
	public List<LocationCity> findByListName(List<String> lstNameCity) {
		
		return locationCitydao.findByListName(lstNameCity);
	}
}
