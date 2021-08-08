package com.Xoot.CreditoParaTi.services.service;

import com.Xoot.CreditoParaTi.services.interfaces.ILocationColoniesService;
import com.Xoot.CreditoParaTi.entity.LocationColonies;
import com.Xoot.CreditoParaTi.repositories.interfaces.ILocationColoniesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import org.modelmapper.ModelMapper;

@Service
public class LocationColoniesImp implements ILocationColoniesService {
	@Autowired
	private ILocationColoniesDao iLocationColoniesDao;
	@Autowired
	private ModelMapper modelMapper;


	@Override
	public List<LocationColonies> saveRange(List<LocationColonies> lstColonies) {
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<LocationColonies> findAllActive() { return iLocationColoniesDao.findAll(); }

	@Override
	@Transactional(readOnly = true)
	public List<LocationColonies> getCityToMun(Integer id) {



		return iLocationColoniesDao.getCityToMun(id);

	}


	@Override
	public List<LocationColonies> findByListName(List<String> lstNameSuburb) {
		return null;
	}
}
