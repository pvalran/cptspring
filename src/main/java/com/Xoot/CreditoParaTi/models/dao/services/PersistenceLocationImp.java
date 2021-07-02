package com.Xoot.CreditoParaTi.models.dao.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.xmlbeans.impl.piccolo.io.FileFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Xoot.CreditoParaTi.Definiciones.Services.ILocationCityService;
import com.Xoot.CreditoParaTi.Definiciones.Services.ILocationService;
import com.Xoot.CreditoParaTi.Definiciones.Services.ILocationStateService;
import com.Xoot.CreditoParaTi.Definiciones.Services.ILocationSuburbService;
import com.Xoot.CreditoParaTi.Definiciones.Services.IPersistenceLocation;
import com.Xoot.CreditoParaTi.entity.LocationCity;
import com.Xoot.CreditoParaTi.entity.LocationState;
import com.Xoot.CreditoParaTi.entity.LocationSuburb;
import com.Xoot.CreditoParaTi.entity.DTO.LocationDTO;

@Service
public class PersistenceLocationImp implements IPersistenceLocation {

	@Autowired 
	private ILocationService LocationService;
	
	@Autowired 
	private ILocationCityService locationCityService;
	
	@Autowired 
	private ILocationStateService locationStateService;
	
	@Autowired 
	private ILocationSuburbService locationSuburbService;
	
	public void persistLocation(MultipartFile reapExcelDataFile) {
		
		try {
		 List<LocationDTO> lstLocations =	LocationService.GetLocations(reapExcelDataFile);
		
		 lstLocations.forEach((location) -> {
			 
			 LocationState locationStateCreated = SaveLocation(location);

			 LocationCity locationCity = SaveLocationCity(location, locationStateCreated);
			 
			 LocationCity locationCityCreated = locationCityService.save(locationCity);

			 SaveLocationSuburb(location, locationCityCreated);
		 });

			 
		} catch (FileFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void SaveLocationSuburb(LocationDTO location, LocationCity locationCityCreated) {
		List<LocationSuburb> lstLocationSuburb = new ArrayList<LocationSuburb>();
		 
		 location.getLstSuburb().forEach((Suburb) -> {
			 
			 LocationSuburb LocationSuburb = new LocationSuburb();
			 
			 LocationSuburb.setName(location.getCity());
			 LocationSuburb.setCity(locationCityCreated);
			 
			 lstLocationSuburb.add(LocationSuburb);
		 
		 });
		 
		 locationSuburbService.saveRange(lstLocationSuburb);
	}

	private LocationCity SaveLocationCity(LocationDTO location, LocationState locationStateCreated) {
		LocationCity locationCity = new LocationCity();
		
		locationCity.setStatus_flag(1);
		
		locationCity.setName(location.getCity());
		
		locationCity.setState(locationStateCreated);
		 
		return locationCity;
	}

	private LocationState SaveLocation(LocationDTO location) {
		LocationState locationState = new LocationState();
		 
		locationState.setName(location.getState());
		 
		locationState.setStatus_flag(1);
		 
		LocationState locationStateCreated = locationStateService.save(locationState);
		 
		return locationStateCreated;
	}

}
