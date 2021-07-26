package com.Xoot.CreditoParaTi.controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Xoot.CreditoParaTi.Definiciones.Services.IDocumentService;
import com.Xoot.CreditoParaTi.entity.Document;
import com.Xoot.CreditoParaTi.entity.DTO.ResponseDTO;
import com.Xoot.CreditoParaTi.models.dao.IDocumentDao;

@RestController
@RequestMapping("/documents/file")
public class DocumentFileController {
	@Autowired
	private IDocumentService documentService;
	@Autowired
	private IDocumentDao documentDao;

	@PostMapping("/uploadfile")
	public ResponseDTO upload(@RequestParam("file") MultipartFile file, @RequestParam("id") Integer id) {
		Object data = null;
		String message = "";
		Boolean result = false;

		try {
			Document document = documentService.findById(id);
			if (document != null) {
				if (!file.isEmpty()) {
					String nombreArchivo = UUID.randomUUID().toString() + "_"
							+ file.getOriginalFilename().replace(" ", "");
					Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();

					try {
						Files.copy(file.getInputStream(), rutaArchivo);
					} catch (IOException e) {
						message = e.getMessage().concat(": ").concat(e.getCause().getMessage());
					}

					String nombreAnterior = document.getName();

					if (nombreAnterior != null && nombreAnterior.length() > 0) {
						Path rutaAnterior = Paths.get("uploads").resolve(nombreAnterior).toAbsolutePath();

						File archivoAnterior = rutaAnterior.toFile();

						if (archivoAnterior.exists() && archivoAnterior.canRead()) {
							archivoAnterior.delete();
						}
					}

					document.setName(nombreArchivo);

					data = documentDao.save(document);
					result = true;
					message = "Imagen subida correctamente.";
				} else {
					message = "No se puede guardar la imagen intentelo de nuevo.";
				}
			} else {
				message = "No existe el documento que quiere guardar.";
			}
		} catch (Exception e) {
			message = "Ocurrió un error al crear la clase de documento.";
		}
		return new ResponseDTO(data, message, result);
	}

	@GetMapping("/dowloadfile/{name:.+}")
	public ResponseEntity<Resource> viewFile(@PathVariable String name) {

		Path rutaArchivo = Paths.get("D://uploadJava").resolve(name).toAbsolutePath();

		Resource recurso = null;

		try {
			recurso = new UrlResource(rutaArchivo.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		if (!recurso.exists() && !recurso.isReadable()) {
			throw new RuntimeException("Error: no se pudo cargar la imagen " + name);
		}

		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");

		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}
}
