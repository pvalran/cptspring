package com.Xoot.CreditoParaTi.models.dao.services;

import java.util.List;

import com.Xoot.CreditoParaTi.entity.Customer;
import com.Xoot.CreditoParaTi.entity.DocumentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Xoot.CreditoParaTi.Definiciones.Services.ILocationStateService;
import com.Xoot.CreditoParaTi.entity.LocationState;
import com.Xoot.CreditoParaTi.models.dao.ILocationStateDao;
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
