package com.Xoot.CreditoParaTi.Definiciones.Services;

import java.util.List;

import org.apache.xmlbeans.impl.piccolo.io.FileFormatException;
import org.springframework.web.multipart.MultipartFile;

import com.Xoot.CreditoParaTi.entity.DTO.LocationDTO;

public interface ILocationService {
	
	public List<LocationDTO> GetLocations(MultipartFile reapExcelDataFile)throws FileFormatException;
}
