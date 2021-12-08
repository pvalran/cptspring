package com.Xoot.CreditoParaTi.services.service;

import com.Xoot.CreditoParaTi.services.interfaces.ILocationCountiesService;
import com.Xoot.CreditoParaTi.entity.app.LocationCounty;
import com.Xoot.CreditoParaTi.repositories.app.ILocationsCountiesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import org.modelmapper.ModelMapper;

@Service
public class LocationCountiesImp implements ILocationCountiesService {
	@Autowired
	private ILocationsCountiesDao iLocationsCountiesDao;
	@Autowired
	private ModelMapper modelMapper;


	@Override
	public List<LocationCounty> saveRange(List<LocationCounty> lstColonies) {
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<LocationCounty> findAllActive() { return iLocationsCountiesDao.findAll(); }

	@Override
	@Transactional(readOnly = true)
	public List<LocationCounty> getMuntoState(Integer id) {
		return iLocationsCountiesDao.getMunToState(id);
	}

	@Override
	public List<LocationCounty> findByListName(List<String> lstNameSuburb) {
		return null;
	}
}
