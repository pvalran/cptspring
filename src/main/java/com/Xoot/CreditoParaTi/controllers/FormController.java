package com.Xoot.CreditoParaTi.controllers;

import com.Xoot.CreditoParaTi.Definiciones.Services.*;
import com.Xoot.CreditoParaTi.entity.*;
import com.Xoot.CreditoParaTi.entity.DTO.*;
import com.Xoot.CreditoParaTi.models.dao.ICreditApplicationDao;
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
import org.apache.commons.io.FileUtils;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
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
    private IAdditionalInformationService additionalInformationService;
    @Autowired
    private IEconomicDependientiesService economicDependientiesService;
    @Autowired
    private ISpouseService spouseService;
    @Autowired
    private IWorkService workService;
    @Autowired
    private IReferenceService referenceService;
    @Autowired
    private IPropertyService propertyService;
    @Autowired
    private IAnswerMedicalquestionnaireService answerMedicalquestionnaireService;
    @Autowired
    private IMedicalQuestionnaireService medicalQuestionnaireService;
    @Autowired
    private IAnswerQuestionnaireService answerQuestionnaireService;
    @Autowired
    private IFreeQuestionnaireService freeQuestionnaireService;

    @Autowired
    private ICreditApplicationDao creditApplicationDao;

    @Autowired
    private ModelMapper modelMapper;

    private final Path root = Paths.get("/srv/www/upload");

    @GetMapping("/customer/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO FormCustomer(@PathVariable Integer id) {
        try {
            return new ResponseDTO( modelMapper.map(customerService.findById(id),Customer.class),"Informacion de OCR",true);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDTO(null, "Ocurrió un error al crear el cliente.", false);
        }
    }

    @PostMapping("/customer")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO FormCustomer(@RequestBody CustomerDTO customer) {
        Customer customerResponse;
        CreditApplicationDTO creditApplicationDTO;
        try {
            ResponseDTO responseDTO = customerService.save(customer);
            customerResponse = (Customer) responseDTO.getData();
            Integer IdCustomer = customerResponse.getIdCustomer();


            CreditApplication creditApplication = creditApplicationDao.FindByCreditUser(customer.getCreditId());

            if (creditApplication != null) {
                creditApplicationDTO = modelMapper.map(creditApplication,CreditApplicationDTO.class);
                creditApplicationDTO.setCustomer(IdCustomer);
                creditApplicationDTO.setProduct(customer.getCreditsAplicationProducts());
                creditApplicationDTO.setUser(customer.getUserId());
                creditApplicationService.update(creditApplicationDTO.getIdCreditAplication(),creditApplicationDTO);
            } else {
                creditApplicationDTO = new CreditApplicationDTO();
                creditApplicationDTO.setCustomer(IdCustomer);
                creditApplicationDTO.setProduct(customer.getCreditsAplicationProducts());
                creditApplicationDTO.setUser(customer.getUserId());
                creditApplicationDTO.setCreditId(customer.getCreditId());
                creditApplicationService.save(creditApplicationDTO);
            }

            return responseDTO;
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
    public ResponseDTO FormUpload(@RequestBody UploadDTO upload) {
        Object data = null;
        String message = "";
        Boolean result = false;
        String namefile;

        String file = upload.getFile();
        Integer creditID = upload.getCreditId();
        Integer documentTypeID = upload.getDocumentTypeId();
        Integer userID = upload.getUserId();

        try {
            if (file != null) {
                try {
                    namefile = UUID.randomUUID().toString();
                    Path pathFile = this.root.resolve(namefile);
                    if (Files.exists(pathFile)) {
                        File filename = pathFile.toFile();
                        filename.delete();
                    }
                    File filename = pathFile.toFile();
                    byte[] decodedBytes = Base64.getDecoder().decode(file);
                    FileUtils.writeByteArrayToFile(filename, decodedBytes);
                } catch (Exception e) {
                    throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
                }
                DocumentDTO documentDTO = new DocumentDTO();
                documentDTO.setCreditAplication(creditID);
                documentDTO.setTypeDocumentId(documentTypeID);
                documentDTO.setClassDocumentId(3);
                documentDTO.setName(namefile);
                documentDTO.setUserId(userID);

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

    @GetMapping("/download/{id}")
    public ResponseDTO viewFile(@PathVariable Integer id) {
        Resource recurso = null;
        Document document = documentService.findById(id);
        Path rutaArchivo = Paths.get("/srv/www/upload").resolve(document.getName()).toAbsolutePath();

        File file = rutaArchivo.toFile();

        try {
            byte[] fileContent = FileUtils.readFileToByteArray(file);
            String base64File = Base64.getEncoder()
                    .encodeToString(fileContent);
            return new ResponseDTO(base64File, "Descarga de archivo.", true);
        } catch (IOException e) {
            return new ResponseDTO(e.getMessage(), "Ocurrió un error en la descargar.", false);
        }
    }

    @GetMapping("/download/{creditId}/{idType}")
    public ResponseDTO viewFile(@PathVariable Integer creditId,@PathVariable Integer idType) {
        Resource recurso = null;
        Document document = documentService.findAllIds(creditId,idType);
        Path rutaArchivo = Paths.get("/srv/www/upload").resolve(document.getName()).toAbsolutePath();

        File file = rutaArchivo.toFile();

        try {
            byte[] fileContent = FileUtils.readFileToByteArray(file);
            String base64File = Base64.getEncoder()
                    .encodeToString(fileContent);
            return new ResponseDTO(base64File, "Descarga de archivo.", true);
        } catch (IOException e) {
            return new ResponseDTO(e.getMessage(), "Ocurrió un error en la descargar.", false);
        }
    }



    @GetMapping("/downfilename/{name}")
    public ResponseDTO viewFile(@PathVariable String name) {
        Resource recurso = null;
        Path rutaArchivo = Paths.get("/srv/www/upload").resolve(name).toAbsolutePath();
        File file = rutaArchivo.toFile();

        try {
            byte[] fileContent = FileUtils.readFileToByteArray(file);
            String base64File = Base64.getEncoder().encodeToString(fileContent);
            return new ResponseDTO(base64File, "Descarga de archivo.", true);
        } catch (IOException e) {
            return new ResponseDTO(e.getMessage(), "Ocurrió un error en la descargar.", false);
        }
    }

    @GetMapping("/additionalInformation/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO FormAdditionalInformation(@PathVariable Integer id) {
        try {
            return new ResponseDTO( modelMapper.map(additionalInformationService.findById(id),AdditionalInformationDTO.class),"Información de datos adicionales",true);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDTO(null, "Ocurrió un error al registrar la información adicional.", false);
        }
    }

    @PostMapping("/additionalInformation")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO FormAdditionalInformation(@RequestBody AdditionalInformationDTO additionalInformation) {
        Customer customerResponse;
        try {
           return additionalInformationService.save(additionalInformation);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDTO(null, "Ocurrió un error en creación de los datos adicionales.", false);
        }
    }

    @PutMapping("/additionalInformation/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO FormAdditionalInformation(@PathVariable Integer id,@RequestBody AdditionalInformationDTO additionalInformationDTO) {
        try {
            return additionalInformationService.update(id,additionalInformationDTO);
        } catch (Exception e) {
            return new ResponseDTO(null, "Ocurrió un error en la actualización de los datos adicionales.", false);
        }
    }

    @GetMapping("/economicdependenty/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO FormEconomicDependenty(@PathVariable Integer id) {
        try {
            return new ResponseDTO( modelMapper.map(economicDependientiesService.findById(id),EconomicDependientiesDto.class),"Información del formulario de dependientes economicos",true);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDTO(null, "Ocurrió un error en la información de los dependientes economicos.", false);
        }
    }

    @PostMapping("/economicdependenty")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO FormEconomicDependenty(@RequestBody EconomicDependientiesDto economicDependientiesDto) {
        Customer customerResponse;
        try {
            return economicDependientiesService.save(economicDependientiesDto);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDTO(null, "Ocurrió un error en la creación de los datos de dependientes economicos.", false);
        }
    }

    @PutMapping("/economicdependenty/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO FormEconomicDependenty(@PathVariable Integer id,@RequestBody EconomicDependientiesDto economicDependientiesDto) {
        try {
            return economicDependientiesService.update(id,economicDependientiesDto);
        } catch (Exception e) {
            return new ResponseDTO(null, "Ocurrió un error en la actualización de los datos de los dependientes economicos.", false);
        }
    }

    @GetMapping("/spouse/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO FormSpouse(@PathVariable Integer id) {
        try {
            SpouseDTO spouseDTO = modelMapper.map(spouseService.findById(id),SpouseDTO.class);

            return new ResponseDTO(spouseDTO,"Informacion del conyuge",true);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDTO(null, "Ocurrió un error en la información del coyuge.", false);
        }
    }

    @PostMapping("/spouse")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO FormSpouse(@RequestBody SpouseDTO spouseDTO) {
        Customer customerResponse;
        try {
            return spouseService.save(spouseDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDTO(null, "Ocurrió un error en la creación de los datos del coyuge.", false);
        }
    }

    @PutMapping("/spouse/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO FormSpouse(@PathVariable Integer id,@RequestBody SpouseDTO spouseDTO) {
        try {
            return spouseService.update(id,spouseDTO);
        } catch (Exception e) {
            return new ResponseDTO(null, "Ocurrió un error en la actualización de los datos del coyuge.", false);
        }
    }

    @GetMapping("/work/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO FormWork(@PathVariable Integer id) {
        try {
            return new ResponseDTO( modelMapper.map(workService.findById(id),WorkDTO.class),"Información  de los datos laborales",true);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDTO(null, "Ocurrió un error en la información laboral.", false);
        }
    }

    @PostMapping("/work")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO FormWork(@RequestBody WorkDTO workDTO) {
        try {
            return workService.save(workDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDTO(null, "Ocurrió un error en la creación de los datos laborales.", false);
        }
    }

    @PutMapping("/work/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO FormWork(@PathVariable Integer id,@RequestBody WorkDTO workDTO) {
        try {
            return workService.update(id,workDTO);
        } catch (Exception e) {
            return new ResponseDTO(null, "Ocurrió un error en la actualización de los datos laborales.", false);
        }
    }

    @GetMapping("/reference/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO FormReference(@PathVariable Integer id) {
        try {
            return new ResponseDTO( modelMapper.map(referenceService.findById(id),ReferenceDTO.class),"Información de datos de referencias personales",true);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDTO(null, "Ocurrió un error en la información de los datos de referencias personales.", false);
        }
    }

    @PostMapping("/reference")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO FormReference(@RequestBody ReferenceDTO referenceDTO) {
        try {
            return referenceService.save(referenceDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDTO(null, "Ocurrió un error en la creación de los datos de referencias personales.", false);
        }
    }

    @PutMapping("/reference/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO FormReference(@PathVariable Integer id,@RequestBody ReferenceDTO referenceDTO) {
        try {
            return referenceService.update(id,referenceDTO);
        } catch (Exception e) {
            return new ResponseDTO(null, "Ocurrió un error en la actualización de los datos de referencias personales.", false);
        }
    }

    @GetMapping("/property/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO FormProperty(@PathVariable Integer id) {
        try {
            return new ResponseDTO( modelMapper.map(propertyService.findById(id),PropertyDTO.class),"Información de los datos de inmuebles",true);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDTO(null, "Ocurrió un error en la información de los datos de los inmuebles.", false);
        }
    }

    @PostMapping("/property")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO FormProperty(@RequestBody PropertyDTO propertyDTO) {
        try {
            return propertyService.save(propertyDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDTO(null, "Ocurrió un error en la creación de los datos de los inmuebles.", false);
        }
    }

    @PutMapping("/property/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO FormProperty(@PathVariable Integer id,@RequestBody PropertyDTO propertyDTO) {
        try {
            return propertyService.update(id,propertyDTO);
        } catch (Exception e) {
            return new ResponseDTO(null, "Ocurrió un error en la actualización de los datos de los inmuebles.", false);
        }
    }

    @GetMapping("/medicalanswerquestionnaire/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO FormMedicalquestionnaire(@PathVariable Integer id) {
        try {
            return answerMedicalquestionnaireService.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDTO(null, "Ocurrió un error en el sistema.", false);
        }
    }

    @PostMapping("/medicalanswerquestionnaire")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO FormMedicalquestionnaire(@RequestBody MedicalQuestionnaireAnswerDTO medicalQuestionnaireAnswerDTO) {
        try {
            return answerMedicalquestionnaireService.save(medicalQuestionnaireAnswerDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDTO(null, "Ocurrió un error en la creación del cuestionario medico.", false);
        }
    }

    @PutMapping("/medicalanswerquestionnaire/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO FormMedicalquestionnaire(@PathVariable Integer id,@RequestBody MedicalQuestionnaireAnswerDTO medicalQuestionnaireAnswerDTO) {
        try {
            return new ResponseDTO(null, "En desarrollo.", true);
        } catch (Exception e) {
            return new ResponseDTO(null, "Ocurrió un error en la actualización del cuestionario medico.", false);
        }
    }
}
