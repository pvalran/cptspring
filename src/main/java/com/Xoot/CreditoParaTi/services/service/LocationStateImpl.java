package com.Xoot.CreditoParaTi.services.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Xoot.CreditoParaTi.services.interfaces.ILocationStateService;
import com.Xoot.CreditoParaTi.entity.app.LocationState;
import com.Xoot.CreditoParaTi.repositories.app.ILocationStateDao;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LocationStateImpl implements ILocationStateService {

	@Autowired
	private ILocationStateDao locationStatedao;



	@Override
	public LocationState save(LocationState locationState) {
		return locationStatedao.save(locationState);
	}




	@Override
	@Transactional(readOnly = true)
	public List<LocationState> findAllActive() { return locationStatedao.findAll(); }



	@Override
	public List<LocationState> findByListName(List<String> lstNameState) {
		
		return locationStatedao.findByListName(lstNameState);
	}
}
