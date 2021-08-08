package com.Xoot.CreditoParaTi.services.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface IPersistenceLocation {
	 
	void persistLocation(MultipartFile reapExcelDataFile);

}
