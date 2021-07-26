package com.Xoot.CreditoParaTi.controllers;

import com.Xoot.CreditoParaTi.Definiciones.Services.*;
import com.Xoot.CreditoParaTi.entity.*;
import com.Xoot.CreditoParaTi.entity.DTO.*;
import com.Xoot.CreditoParaTi.models.dao.IDocumentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import java.lang.reflect.Type;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;


@RestController
@RequestMapping("/forms")
public class FormController {

    @Autowired
    private ICreditApplicationService creditApplicationService;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IDocumentTypeService documentTypeService;
    @Autowired
    private IDocumentService documentService;
    @Autowired
    private IDocumentDao documentDao;
    @Autowired
    private ModelMapper modelMapper;

    private final Path root = Paths.get("/srv/www/upload");


    @PostMapping("/customer")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO FormCustomer(@RequestBody CustomerDTO customer) {
        Customer customerResponse;
        try {
            ResponseDTO responseDTO = customerService.save(customer);
            customerResponse = (Customer) responseDTO.getData();
            Integer IdCustomer = customerResponse.getIdCustomer();
            CreditApplicationDTO creditApplication = new CreditApplicationDTO();
            creditApplication.setCustomer_id(IdCustomer);
            creditApplication.setProduct_id(customer.getCreditsAplicationProducts());
            creditApplication.setUser_id(customer.getUserId());
            return creditApplicationService.save(creditApplication);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDTO(null, "Ocurrió un error al crear el cliente.", false);
        }
    }

    @PutMapping("/customer/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO FormCustomer(@PathVariable Integer id,@RequestBody CustomerDTO customer) {
        try {
            return customerService.update(id,customer);
        } catch (Exception e) {
            return new ResponseDTO(null, "Ocurrió un error al crear el cliente.", false);
        }
    }

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO FormDocument(@RequestParam("file") MultipartFile file,
                                    @RequestParam("creditId") Integer creditID,
                                    @RequestParam("documentTypeId") Integer documentTypeID
    ) {
        Object data = null;
        String message = "";
        Boolean result = false;

        try {
            if (!file.isEmpty()) {
                try {
                    Path pathFile = this.root.resolve(file.getOriginalFilename());
                    if (Files.exists(pathFile)) {
                        File filename = pathFile.toFile();
                        filename.delete();
                    }
                    Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
                } catch (Exception e) {
                    throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
                }

                String nombreArchivo = file.getOriginalFilename();

                /*String nombreArchivo = UUID.randomUUID().toString() + "_"
                        + file.getOriginalFilename().replace(" ", "");
                Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();

                try {
                    Files.copy(file.getInputStream(), rutaArchivo);
                } catch (IOException e) {
                    message = e.getMessage().concat(": ").concat(e.getCause().getMessage());
                }*/

                /*String nombreAnterior = document.getName();

                if (nombreAnterior != null && nombreAnterior.length() > 0) {
                    Path rutaAnterior = Paths.get("uploads").resolve(nombreAnterior).toAbsolutePath();

                    File archivoAnterior = rutaAnterior.toFile();

                    if (archivoAnterior.exists() && archivoAnterior.canRead()) {
                        archivoAnterior.delete();
                    }
                }*/

                DocumentDTO documentDTO = new DocumentDTO();
                documentDTO.setCreditAplicationId(creditID);
                documentDTO.setTypeDocumentId(documentTypeID);
                documentDTO.setClassDocumentId(2);
                documentDTO.setName(nombreArchivo);
                ResponseDTO responseDTO = documentService.save(documentDTO);
                Document document = (Document) responseDTO.getData();
                documentDTO = modelMapper.map(document,DocumentDTO.class);
                responseDTO.setData(documentDTO);
                return responseDTO;
            } else {
                message = "No se puede guardar la imagen intentelo de nuevo.";
            }

        } catch (Exception e) {
            e.printStackTrace();
            message = "Error al suber el archivo";
        }
        return new ResponseDTO(data, message, result);
    }

    @GetMapping("/download/{name}")
    public ResponseEntity<Resource> viewFile(@PathVariable Integer name) {
        Resource recurso = null;
        Document document = documentService.findById(name);
        Path rutaArchivo = Paths.get("/srv/www/upload").resolve(document.getName()).toAbsolutePath();

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

        //return new ResponseDTO(document,rutaArchivo.toUri().toString(),true);
    }



}
