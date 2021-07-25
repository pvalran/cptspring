package com.Xoot.CreditoParaTi.models.dao.services;

import com.Xoot.CreditoParaTi.Definiciones.Services.ILocationColoniesService;
import com.Xoot.CreditoParaTi.entity.LocationColonies;
import com.Xoot.CreditoParaTi.models.dao.ILocationColoniesDao;
import com.Xoot.CreditoParaTi.models.dao.ILocationSuburbDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LocationColoniesImp implements ILocationColoniesService {
	@Autowired
	private ILocationColoniesDao iLocationColoniesDao;

	@Override
	public List<LocationColonies> saveRange(List<LocationColonies> lstColonies) {
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<LocationColonies> findAllActive() { return iLocationColoniesDao.findAll(); }

	@Override
	@Transactional(readOnly = true)
	public List<LocationColonies> getCityToMun(Integer id) { return iLocationColoniesDao.getCityToMun(id); }


	@Override
	public List<LocationColonies> findByListName(List<String> lstNameSuburb) {
		return null;
	}
}
