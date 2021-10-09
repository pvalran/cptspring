package com.Xoot.CreditoParaTi.services.service;

import java.lang.reflect.Type;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Xoot.CreditoParaTi.services.interfaces.ILocationCityService;
import com.Xoot.CreditoParaTi.entity.LocationCity;
import com.Xoot.CreditoParaTi.repositories.interfaces.ILocationCityDao;
import com.Xoot.CreditoParaTi.dto.LocationsCitiesDTO;
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
	public List<LocationCity> findAllActive() {
		return locationCitydao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<LocationsCitiesDTO> getCityByMun(Integer id) {
		Type listType = new TypeToken<List<LocationsCitiesDTO>>() {
		}.getType();
		return modelMapper.map(locationCitydao.getCityByMun(id), listType);
	}

	@Override
	@Transactional(readOnly = true)
	public List<LocationsCitiesDTO> getCityToMun(String state,String counties) {
		Type listType = new TypeToken<List<LocationsCitiesDTO>>() {}.getType();
		return modelMapper.map(locationCitydao.getCityToMun(state,counties), listType);
	}

	@Override
	public List<LocationCity> findByListName(List<String> lstNameCity) {
		return null;
	}
}
