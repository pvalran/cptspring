package com.Xoot.CreditoParaTi.Definiciones.Services;

import org.springframework.web.multipart.MultipartFile;

public interface IPersistenceLocation {
	 
	void persistLocation(MultipartFile reapExcelDataFile);

}
