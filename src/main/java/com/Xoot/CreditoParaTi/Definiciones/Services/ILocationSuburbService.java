package com.Xoot.CreditoParaTi.Definiciones.Services;


import java.util.List;

import com.Xoot.CreditoParaTi.entity.LocationSuburb;


public interface ILocationSuburbService {
	
	List<LocationSuburb> saveRange(List<LocationSuburb> lstSuburb);

	List<LocationSuburb> findByListName(List<String> lstNameSuburb);

}
