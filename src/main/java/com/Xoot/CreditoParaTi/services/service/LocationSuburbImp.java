package com.Xoot.CreditoParaTi.services.service;

import java.util.List;
import java.lang.reflect.Type;

import com.Xoot.CreditoParaTi.dto.LocationsSuburbDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Xoot.CreditoParaTi.services.interfaces.ILocationSuburbService;
import com.Xoot.CreditoParaTi.entity.LocationSuburb;
import com.Xoot.CreditoParaTi.repositories.interfaces.ILocationSuburbDao;
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
	public List<LocationSuburb> getSuburbByMun(Integer id) { return iLocationSuburbDao.getSuburbByMun(id);  }

	@Override
	@Transactional(readOnly = true)
	public List<LocationSuburb> getSuburbByCity(Integer id) { return iLocationSuburbDao.getSuburbByCity(id);  }

	@Override
	@Transactional(readOnly = true)
	public List<LocationSuburb> getSuburbToCity(String state, String counties, String cities) { return iLocationSuburbDao.getSuburbToCity(state,counties,cities);  }

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
		
		return null;
	}

}
