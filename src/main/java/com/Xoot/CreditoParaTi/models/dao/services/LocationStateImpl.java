package com.Xoot.CreditoParaTi.models.dao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Xoot.CreditoParaTi.Definiciones.Services.ILocationStateService;
import com.Xoot.CreditoParaTi.entity.LocationState;
import com.Xoot.CreditoParaTi.models.dao.ILocationStateDao;

@Service
public class LocationStateImpl implements ILocationStateService {

	@Autowired
	private ILocationStateDao locationStatedao;
	
	@Override
	public LocationState save(LocationState locationState) {
		return locationStatedao.save(locationState);
	}
}
