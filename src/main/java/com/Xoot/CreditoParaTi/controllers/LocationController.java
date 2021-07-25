package com.Xoot.CreditoParaTi.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Xoot.CreditoParaTi.Definiciones.Services.IPersistenceLocation;

import springfox.documentation.service.ResponseMessage;

@RestController
@RequestMapping("/Locationcity")
public class LocationController {

	@Autowired
	private IPersistenceLocation persitenceLocation;

	@PostMapping("/importLocations")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ResponseMessage> importLocations(@RequestParam("file") MultipartFile reapExcelDataFile) {
		String message = "";
		try {
			persitenceLocation.persistLocation(reapExcelDataFile);
			message = "Se importo correctamente la información del archivo: " + reapExcelDataFile.getOriginalFilename();
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(0, message, null, null, null));
		} catch (Exception e) {
			message = "Ocurrió un error al importar el archivo " + reapExcelDataFile.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(0, message, null, null, null));
		}
	}
}
