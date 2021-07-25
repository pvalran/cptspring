package com.Xoot.CreditoParaTi.models.dao.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.piccolo.io.FileFormatException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Xoot.CreditoParaTi.Definiciones.Services.ILocationService;
import com.Xoot.CreditoParaTi.entity.DTO.LocationDTO;
import com.Xoot.CreditoParaTi.entity.DTO.SuburbDTO;

@Service
public class LocationImpl implements ILocationService {

	public List<LocationDTO> GetLocations(MultipartFile reapExcelDataFile) throws FileFormatException {

		String inputFilename = reapExcelDataFile.getOriginalFilename();
		List<LocationDTO> lstLocation = new ArrayList<LocationDTO>();


		switch (inputFilename.substring(inputFilename.lastIndexOf(".") + 1, inputFilename.length())) {
		case "xlsx":


			XSSFWorkbook workbook;
	
			try {
				workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
				
				XSSFSheet worksheet = workbook.getSheetAt(0);

				for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {

					RecordRows(lstLocation, worksheet, i);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
		default:
			throw new FileFormatException("Formato no admitido");
		}
		
		return lstLocation;

	}

	private void RecordRows(List<LocationDTO> lstLocation, XSSFSheet worksheet, int i) {
		LocationDTO locationDTO = new LocationDTO();

		XSSFRow row = worksheet.getRow(i);

		String ZipCode = (String) row.getCell(6).getStringCellValue();
		String Suburb =  (String) row.getCell(1).getStringCellValue();

		if(!ZipCode.isEmpty() && !Suburb.isEmpty()) {
			
			boolean foundLocation = lstLocation.stream().anyMatch(p -> p.getZipCode().equals(ZipCode));

			if (foundLocation) {

				AddSuburb(lstLocation, ZipCode, Suburb);

			} else {

				AddLocation(lstLocation, locationDTO, row, ZipCode, Suburb);
			}
		}
	
	}

	private void AddLocation(List<LocationDTO> lstLocation, LocationDTO locationDTO, XSSFRow row, String ZipCode,
			String Suburb) {

		String State = (String) row.getCell(4).getStringCellValue();
		
		String City = (String) row.getCell(5).getStringCellValue();
		
	    if(!State.isEmpty() && !City.isEmpty() && !ZipCode.isEmpty() ){
	    	
	    	locationDTO.setZipCode(ZipCode);

			locationDTO.setState(State);

			locationDTO.setCity(City);

			List<SuburbDTO> lstSuburbDTO = new ArrayList<SuburbDTO>();

			SuburbDTO SuburbDTO = new SuburbDTO(Suburb);

			lstSuburbDTO.add(SuburbDTO);

			locationDTO.setLstSuburb(lstSuburbDTO);

			lstLocation.add(locationDTO);
	    }
	}

	private void AddSuburb(List<LocationDTO> lstLocation, String ZipCode, String Suburb) {

		if(!Suburb.isEmpty() && !ZipCode.isEmpty()) {
			lstLocation.forEach((location) -> {
				String ZipCodeInList = location.getZipCode();
				if (location.getLstSuburb() != null) {
					if (ZipCodeInList.equals(ZipCode)) {
						SuburbDTO SuburbDTO = new SuburbDTO(Suburb);
						location.getLstSuburb().add(SuburbDTO);
					}
				}
			});
		}
	}

}
