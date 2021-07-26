package com.Xoot.CreditoParaTi.models.dao.services;

import java.lang.reflect.Type;
import java.util.List;

import com.Xoot.CreditoParaTi.entity.LocationState;
import org.modelmapper.internal.bytebuddy.description.method.MethodDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Xoot.CreditoParaTi.Definiciones.Services.ILocationCityService;
import com.Xoot.CreditoParaTi.entity.LocationCity;
import com.Xoot.CreditoParaTi.models.dao.ILocationCityDao;
import com.Xoot.CreditoParaTi.entity.DTO.LocationsCitiesDTO;
import org.springframework.transaction.annotation.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

@Service
public class LocationCityImpl implements ILocationCityService {

	@Autowired
	private ILocationCityDao locationCitydao;

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public LocationCity save(LocationCity locationCity) {
		return locationCitydao.save(locationCity);
	}

	@Override
	@Transactional(readOnly = true)
	public List<LocationCity> findAllActive() { return locationCitydao.findAll(); }

	@Override
	@Transactional(readOnly = true)
	public List<LocationsCitiesDTO> getMuntoState(Integer id) {
		Type listType = new TypeToken<List<LocationsCitiesDTO>>() {}.getType();
		return modelMapper.map(locationCitydao.getMuntoState(id),listType);
	}

	@Override
	public List<LocationCity> findByListName(List<String> lstNameCity) {
		
		return locationCitydao.findByListName(lstNameCity);
	}
}
