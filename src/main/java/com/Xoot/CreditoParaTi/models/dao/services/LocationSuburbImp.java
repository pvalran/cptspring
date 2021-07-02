package com.Xoot.CreditoParaTi.models.dao.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Xoot.CreditoParaTi.Definiciones.Services.ILocationSuburbService;
import com.Xoot.CreditoParaTi.entity.LocationSuburb;
import com.Xoot.CreditoParaTi.models.dao.ILocationSuburbDao;

@Service
public class LocationSuburbImp implements ILocationSuburbService {

	@Autowired
	private ILocationSuburbDao  iLocationSuburbDao;
	
	@Override
	public List<LocationSuburb> saveRange(List<LocationSuburb> lstSuburb) {
	
		return (List<LocationSuburb>) iLocationSuburbDao.saveAll(lstSuburb);
	}

}
