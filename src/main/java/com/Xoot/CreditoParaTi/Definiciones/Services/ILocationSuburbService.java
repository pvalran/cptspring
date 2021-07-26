package com.Xoot.CreditoParaTi.Definiciones.Services;


import java.util.List;

import com.Xoot.CreditoParaTi.entity.DTO.LocationsSuburbDTO;
import com.Xoot.CreditoParaTi.entity.LocationCity;
import com.Xoot.CreditoParaTi.entity.LocationSuburb;


public interface ILocationSuburbService {
	
	List<LocationSuburb> saveRange(List<LocationSuburb> lstSuburb);

	List<LocationSuburb> findAllActive();

	List<LocationSuburb> findByListName(List<String> lstNameSuburb);

	List<LocationSuburb> getColonyToMun(Integer id);

	List<LocationsSuburbDTO> getDirectionToCp(Integer zipcode);

}
