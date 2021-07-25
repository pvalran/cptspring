package com.Xoot.CreditoParaTi.controllers;

import com.Xoot.CreditoParaTi.Definiciones.Services.*;
import com.Xoot.CreditoParaTi.entity.*;
import com.Xoot.CreditoParaTi.entity.DTO.*;
import com.Xoot.CreditoParaTi.models.dao.IDocumentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;


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
            creditApplication.setProduct_id(1);
            creditApplication.setStatus_id(1);
            creditApplication.setUser_id(1);
            creditApplicationService.save(creditApplication);
            return customerService.save(customer);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDTO(null, "Ocurrió un error al crear el cliente.", false);
        }
    }

    @PostMapping("/customer/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO FormCustomer(@PathVariable Integer id,@RequestBody CustomerDTO customer) {
        try {
            return customerService.update(id,customer);
        } catch (Exception e) {
            return new ResponseDTO(null, "Ocurrió un error al crear el cliente.", false);
        }
    }

    @PostMapping("/document")
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
}
