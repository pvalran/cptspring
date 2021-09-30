package com.Xoot.CreditoParaTi.services.interfaces;


import java.util.List;

import com.Xoot.CreditoParaTi.dto.LocationsSuburbDTO;
import com.Xoot.CreditoParaTi.entity.LocationSuburb;


public interface ILocationSuburbService {
	
	List<LocationSuburb> saveRange(List<LocationSuburb> lstSuburb);

	List<LocationSuburb> findAllActive();

	List<LocationSuburb> findByListName(List<String> lstNameSuburb);

	List<LocationSuburb> getSuburbByMun(Integer id);

	List<LocationSuburb> getSuburbByCity(Integer id);

	List<LocationSuburb> getSuburbToCity(String state,String counties,String cities);

	List<LocationsSuburbDTO> getDirectionToCp(Integer zipcode);

}
