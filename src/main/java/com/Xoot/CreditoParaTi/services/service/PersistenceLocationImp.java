package com.Xoot.CreditoParaTi.services.service;

import java.util.ArrayList;
import java.util.List;

import com.Xoot.CreditoParaTi.entity.app.LocationCity;
import com.Xoot.CreditoParaTi.entity.app.LocationState;
import com.Xoot.CreditoParaTi.entity.app.LocationSuburb;
import com.Xoot.CreditoParaTi.services.interfaces.*;
import org.apache.xmlbeans.impl.piccolo.io.FileFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.stream.Collectors;

import com.Xoot.CreditoParaTi.dto.LocationDTO;
import com.Xoot.CreditoParaTi.dto.SuburbDTO;

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

			List<LocationDTO> lstLocations = LocationService.GetLocations(reapExcelDataFile);

			List<String> NamesStates = lstLocations.stream().map(LocationDTO::getState).collect(Collectors.toList());

			List<String> NameCitys = lstLocations.stream().map(LocationDTO::getCity).collect(Collectors.toList());

			List<LocationState> lstStateExist = locationStateService.findByListName(NamesStates);

			List<LocationCity> lstCitysExist = locationCityService.findByListName(NameCitys);
			
			lstLocations.forEach((location) -> {

				LocationState locationStateCreated = SaveLocation(location, lstStateExist);

				if (locationStateCreated != null) {
					LocationCity locationCity = SaveLocationCity(location, locationStateCreated, lstCitysExist);

					LocationCity locationCityCreated = locationCityService.save(locationCity);

					if (locationCityCreated != null) {

						SaveLocationSuburb(location, locationCityCreated);
					}
				}
			});

		} catch (FileFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void SaveLocationSuburb(LocationDTO location, LocationCity locationCityCreated) {
		List<LocationSuburb> lstLocationSuburb = new ArrayList<LocationSuburb>();
		
		List<SuburbDTO> lstSuburbDTO = location.getLstSuburb().stream().collect(Collectors.toList());

		List<String> nameSuburb = lstSuburbDTO.stream().map(SuburbDTO::getSuburbName).collect(Collectors.toList());

	    List<LocationSuburb>  lstSuburb =	locationSuburbService.findByListName(nameSuburb);
	    
		List<String> nameSuburbExist = lstSuburb.stream().map(LocationSuburb::getName).collect(Collectors.toList());

		location.getLstSuburb().forEach((Suburb) -> {

			if (!nameSuburbExist.contains(Suburb.getSuburbName())) {

				LocationSuburb LocationSuburb = new LocationSuburb();

				LocationSuburb.setName(Suburb.getSuburbName());
				
				LocationSuburb.setStatus_flag(1);
				
				LocationSuburb.setZipCode(location.getZipCode());
				
			    LocationSuburb.setCountiesId(locationCityCreated.getCountiesId());

				lstLocationSuburb.add(LocationSuburb);
			}

		});
		
		if(lstLocationSuburb.size() > 0) {
			
			locationSuburbService.saveRange(lstLocationSuburb);
		}

	}

	private LocationCity SaveLocationCity(LocationDTO location, LocationState locationStateCreated,
			List<LocationCity> lstCitysExist) {
		LocationCity locationCity = new LocationCity();

		List<String> NamesCitys = lstCitysExist.stream().map(LocationCity::getName).collect(Collectors.toList());

		if (!NamesCitys.contains(location.getCity())) {
			
			locationCity.setStatus_flag(1);

			locationCity.setName(location.getCity());
		} else {
			locationCity = lstCitysExist.stream().filter(locationExist -> locationExist.getName().equals(location.getCity()))
					.findFirst().orElse(null);
		}
		return locationCity;
	}

	private LocationState SaveLocation(LocationDTO location, List<LocationState> lstStateExist) {

		LocationState locationState = new LocationState();

		List<String> NamesStatesExist = lstStateExist.stream().map(LocationState::getName).collect(Collectors.toList());

		if (!NamesStatesExist.contains(location.getState())) {

			locationState.setName(location.getState());

			locationState.setStatus_flag(1);

			locationState = locationStateService.save(locationState);

		} else {

			locationState = lstStateExist.stream()
					.filter(locationExist -> locationExist.getName().equals(location.getState())).findFirst().orElse(null);
		}

		return locationState;
	}

}
