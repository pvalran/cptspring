package com.Xoot.CreditoParaTi.services.interfaces;


import com.Xoot.CreditoParaTi.entity.LocationCounty;

import java.util.List;


public interface ILocationCountiesService {
	
	List<LocationCounty> saveRange(List<LocationCounty> lstColonies);

	List<LocationCounty> findAllActive();

	List<LocationCounty> getMuntoState(Integer id);

	List<LocationCounty> findByListName(List<String> lstNameSuburb);

}
