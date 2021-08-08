package com.Xoot.CreditoParaTi.services.interfaces;


import com.Xoot.CreditoParaTi.entity.LocationColonies;

import java.util.List;


public interface ILocationColoniesService {
	
	List<LocationColonies> saveRange(List<LocationColonies> lstColonies);

	List<LocationColonies> findAllActive();

	List<LocationColonies> getCityToMun(Integer id);

	List<LocationColonies> findByListName(List<String> lstNameSuburb);

}
