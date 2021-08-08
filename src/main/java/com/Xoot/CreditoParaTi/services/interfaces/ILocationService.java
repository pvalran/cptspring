package com.Xoot.CreditoParaTi.services.interfaces;

import java.util.List;

import org.apache.xmlbeans.impl.piccolo.io.FileFormatException;
import org.springframework.web.multipart.MultipartFile;

import com.Xoot.CreditoParaTi.dto.LocationDTO;

public interface ILocationService {
	
	public List<LocationDTO> GetLocations(MultipartFile reapExcelDataFile)throws FileFormatException;
}
