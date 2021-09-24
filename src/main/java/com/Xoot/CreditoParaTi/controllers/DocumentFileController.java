package com.Xoot.CreditoParaTi.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.Xoot.CreditoParaTi.services.interfaces.IDocumentService;
import com.Xoot.CreditoParaTi.entity.Document;
import com.Xoot.CreditoParaTi.dto.ResponseDTO;
import com.Xoot.CreditoParaTi.repositories.interfaces.IDocumentDao;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/documents")
public class DocumentFileController {
	@Autowired
	private IDocumentService documentService;
	@Autowired
	private IDocumentDao documentDao;

	@PostMapping("/file/uploadfile")
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

	@GetMapping("/file/dowloadfile/{name:.+}")
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

	@RequestMapping(path = "/apk", method = RequestMethod.GET)
	public ResponseEntity<Resource> download(String param) throws IOException {
		Path rutaArchivo = Paths.get("/srv/www/upload/apk").resolve("BrokerCPT.apk").toAbsolutePath();
		File file = rutaArchivo.toFile();
		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=BrokerCPT.apk");

		return new ResponseEntity<Resource>(resource, cabecera, HttpStatus.OK);

		/*return ResponseEntity.ok()
				.contentLength(file.length())
				.contentType(MediaType.APPLICATION_OCTET_STREAM)
				.body(resource);
		*/
	}

	@GetMapping("/view/{creditId}/{idType}")
	public ResponseEntity<Resource> viewFile(@PathVariable Integer creditId, @PathVariable Integer idType) {
		try {
			Resource recurso = null;
			Document document = documentService.findAllIds(creditId,idType);
			Path rutaArchivo = Paths.get("/srv/www/upload").resolve(document.getName()).toAbsolutePath();
			File file = rutaArchivo.toFile();

			byte[] fileContent = FileUtils.readFileToByteArray(file);
			String filename;
			InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
			HttpHeaders httpHeaders = new HttpHeaders();
			if (idType == 10 || idType == 11){
				filename =  "doc_"+creditId+"_"+idType+".pdf";
				httpHeaders.setContentType(MediaType.APPLICATION_PDF);
			} else {
				filename =  "doc_"+creditId+"_"+idType+".jpg";
				httpHeaders.setContentType(MediaType.IMAGE_JPEG);
			}
			httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+filename);
			return new ResponseEntity<Resource>(resource, httpHeaders, HttpStatus.OK);
		} catch (IOException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
}
