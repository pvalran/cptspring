package com.Xoot.CreditoParaTi.models.dao.services;

import java.util.List;
import java.lang.reflect.Type;

import com.Xoot.CreditoParaTi.entity.DTO.LocationsSuburbDTO;
import com.Xoot.CreditoParaTi.entity.LocationCity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Xoot.CreditoParaTi.Definiciones.Services.ILocationSuburbService;
import com.Xoot.CreditoParaTi.entity.LocationSuburb;
import com.Xoot.CreditoParaTi.models.dao.ILocationSuburbDao;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LocationSuburbImp implements ILocationSuburbService {

	@Autowired
	private ILocationSuburbDao  iLocationSuburbDao;

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<LocationSuburb> saveRange(List<LocationSuburb> lstSuburb) {
	
		return (List<LocationSuburb>) iLocationSuburbDao.saveAll(lstSuburb);
	}

	@Override
	@Transactional(readOnly = true)
	public List<LocationSuburb> findAllActive() { return iLocationSuburbDao.findAll(); }

	@Override
	@Transactional(readOnly = true)
	public List<LocationSuburb> getColonyToMun(Integer id) { return iLocationSuburbDao.getColonyToMun(id);  }

	@Override
	@Transactional(readOnly = true)
	public List<LocationsSuburbDTO> getDirectionToCp(Integer zipcode) {
		Type listType = new TypeToken<List<LocationsSuburbDTO>>() {}.getType();
		List<LocationSuburb> locationsSuburb = iLocationSuburbDao.getDirectionToCp(zipcode);
		List<LocationsSuburbDTO> locationsSuburbDTO = modelMapper.map(locationsSuburb,listType);
		return locationsSuburbDTO;
	}


	@Override
	public List<LocationSuburb> findByListName(List<String> lstNameSuburb) {
		
		return iLocationSuburbDao.findByListName(lstNameSuburb);
	}

}
