package com.Xoot.CreditoParaTi.controllers;

import com.Xoot.CreditoParaTi.dto.*;
import com.Xoot.CreditoParaTi.entity.app.CreditApplication;
import com.Xoot.CreditoParaTi.entity.app.Customer;
import com.Xoot.CreditoParaTi.entity.app.Document;
import com.Xoot.CreditoParaTi.entity.app.Usuario;
import com.Xoot.CreditoParaTi.repositories.app.ICreditApplicationDao;
import com.Xoot.CreditoParaTi.repositories.app.IDocumentDao;
import com.Xoot.CreditoParaTi.services.interfaces.*;
import com.google.gson.Gson;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedWriter;
import java.io.File;
import org.apache.commons.io.FileUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;


@RestController
@CrossOrigin(origins = "*")
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
    private ITransactionService transactionService;
    @Autowired
    private IAnswerQuestionnaireService answerQuestionnaireService;
    @Autowired
    private IFreeQuestionnaireService freeQuestionnaireService;

    @Autowired
    private ICocreditedWorkService cocreditedWorkService;
    @Autowired
    private ICocreditedCustomersService cocreditedCustomersService;
    @Autowired
    private ICocreditedAdditionalService cocreditedAdditionalService;

    @Autowired
    private ICreditApplicationDao creditApplicationDao;

    @Autowired
    private IUserService userService;

    @Autowired
    private ModelMapper modelMapper;

    private final Path root = Paths.get("/srv/www/upload");

    @GetMapping("/customer/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO FormCustomer(@PathVariable Integer id) {
        try {
            return new ResponseDTO( modelMapper.map(customerService.findById(id), Customer.class),"Informacion de OCR",true);
        } catch (Exception e) {
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
        byte[] decodedBytes;

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
                    decodedBytes = Base64.getDecoder().decode(file);
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
                documentDTO.setDocument(decodedBytes);

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

    @GetMapping("/transaction")
    public ResponseDTO FormTransaction() {
        try {
            return new ResponseDTO(transactionService.findAllActive(), "Exito", true);
        } catch (Exception e) {
            return new ResponseDTO(null, e.getMessage(), false);
        }
    }

    @GetMapping("/transaction/{creditId}/{type}")
    public ResponseDTO FormTransaction(@PathVariable("creditId") Integer creditId,@PathVariable("type") Integer typeTransaction) {
        try {
            return transactionService.findValidate(creditId,typeTransaction);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDTO(null, e.getMessage(), false);
        }
    }

    @PostMapping("/transaction")
    public ResponseDTO FormTransaction(@RequestBody TransactionDTO transactionDTO) {
        try {
            Integer userID;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            CreditApplication creditApplication = creditApplicationDao.FindByCreditUser(transactionDTO.getCreditApplication());
            if (creditApplication != null) {
                userID = creditApplication.getUser();
            } else {
                return new ResponseDTO(null, "El id del credito no existe", false);
            }
            try {
                Path pathFile = this.root.resolve("LogTranstions.txt");
                File filelog = pathFile.toFile();
                Gson gjson = new Gson();
                String jsonTransaction = gjson.toJson(transactionDTO);
                String strDate = format.format(new Date());
                jsonTransaction = "\n[" + strDate + "]: " + jsonTransaction;
                FileWriter fileWriter = new FileWriter(filelog,true);
                BufferedWriter bffWriter = new BufferedWriter(fileWriter);
                bffWriter.write(jsonTransaction);
                bffWriter.close();
                fileWriter.close();
            } catch (IOException ex) {

            }
            return transactionService.save(transactionDTO);
        } catch (Exception e) {
            return new ResponseDTO(null, e.getMessage(), false);
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
            return new ResponseDTO( modelMapper.map(additionalInformationService.findById(id), AdditionalInformationDTO.class),"Información de datos adicionales",true);
        } catch (Exception e) {
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
            return new ResponseDTO(null, "Ocurrió un error en la creación de los datos de dependientes economicos.", false);
        }
    }

    @PutMapping("/economicdependenty/{creditId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO FormEconomicDependentyRemove(@PathVariable Integer creditId,@RequestBody EconomicDependientiesDto economicDto) {
        try {
            return economicDependientiesService.remove(creditId);
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
            return new ResponseDTO( modelMapper.map(workService.findById(id), WorkDTO.class),"Información  de los datos laborales",true);
        } catch (Exception e) {
            return new ResponseDTO(null, "Ocurrió un error en la información laboral.", false);
        }
    }

    @PostMapping("/work")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO FormWork(@RequestBody WorkDTO workDTO) {
        try {
            return workService.save(workDTO);
        } catch (Exception e) {
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
            return new ResponseDTO( modelMapper.map(referenceService.findById(id), ReferenceDTO.class),"Información de datos de referencias personales",true);
        } catch (Exception e) {
            return new ResponseDTO(null, "Ocurrió un error en la información de los datos de referencias personales.", false);
        }
    }

    @PostMapping("/reference")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO FormReference(@RequestBody ReferenceDTO referenceDTO) {
        try {
            return referenceService.save(referenceDTO);
        } catch (Exception e) {
            return new ResponseDTO(null, "Ocurrió un error en la creación de los datos de referencias personales.", false);
        }
    }

    @PutMapping("/reference/{creditId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO FormReferenceRemove(@PathVariable Integer creditId,@RequestBody ReferenceDTO referenceDTO) {
        try {
            return referenceService.remove(creditId);
        } catch (Exception e) {
            return new ResponseDTO(null, "Ocurrió un error en la información de los dependientes economicos.", false);
        }
    }

    @GetMapping("/property/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO FormProperty(@PathVariable Integer id) {
        try {
            PropertyDTO propertyDTO = modelMapper.map(propertyService.findById(id),PropertyDTO.class);
            return new ResponseDTO( propertyDTO,"Información de los datos de inmuebles",true);
        } catch (Exception e) {
            return new ResponseDTO(null, "Ocurrió un error en la información de los datos de los inmuebles.", false);
        }
    }

    @PostMapping("/property")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO FormProperty(@RequestBody PropertyDTO propertyDTO) {
        try {
            return propertyService.save(propertyDTO);
        } catch (Exception e) {
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
            return new ResponseDTO(answerMedicalquestionnaireService.findById(id), "Informacion del cuestionario medico.", true);
        } catch (Exception e) {
            return new ResponseDTO(null, "Ocurrió un error en el sistema.", false);
        }
    }

    @PostMapping("/medicalanswerquestionnaire")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO FormMedicalquestionnaire(@RequestBody MedicalQuestionnaireAnswerDTO medicalQuestionnaireAnswerDTO) {
        try {
            return answerMedicalquestionnaireService.save(medicalQuestionnaireAnswerDTO);
        } catch (Exception e) {
            return new ResponseDTO(null, "Ocurrió un error en la creación del cuestionario medico.", false);
        }
    }

    @PutMapping("/medicalanswerquestionnaire/{creditID}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO FormMedicalquestionnaire(@PathVariable Integer creditID,@RequestBody MedicalQuestionnaireAnswerDTO medicalQuestionnaireAnswerDTO) {
        try {
            return answerMedicalquestionnaireService.remove(creditID);
        } catch (Exception e) {
            return new ResponseDTO(null, "Ocurrió un error en la actualización del cuestionario medico.", false);
        }
    }

    @GetMapping("/cocreditedcustomer/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO FormCocreditedCustomer(@PathVariable Integer id) {
        try {
            return new ResponseDTO( modelMapper.map(cocreditedCustomersService.findById(id), CocreditedCustomersDTO.class),"Información general del coacreditado",true);
        } catch (Exception e) {
            return new ResponseDTO(null, "Ocurrió un error al registrar la información adicional.", false);
        }
    }

    @PostMapping("/cocreditedcustomer")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO FormCocreditedCustomer(@RequestBody CocreditedCustomersDTO ObjDTO) {
        Customer customerResponse;
        try {
            return cocreditedCustomersService.save(ObjDTO);
        } catch (Exception e) {
            return new ResponseDTO(null, "Ocurrió un error en creación de generales del coacreditado.", false);
        }
    }

    @PutMapping("/cocreditedcustomer/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO FormCocreditedCustomer(@PathVariable Integer id,@RequestBody CocreditedCustomersDTO ObjDTO) {
        try {
            return cocreditedCustomersService.update(id,ObjDTO);
        } catch (Exception e) {
            return new ResponseDTO(null, "Ocurrió un error en la actualización de los datos generales del coacreditado.", false);
        }
    }

    @GetMapping("/cocreditedadditional/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO FormCocreditedAdditional(@PathVariable Integer id) {
        try {
            return new ResponseDTO( modelMapper.map(cocreditedAdditionalService.findById(id), CocreditedAdditionalDTO.class),"Información general de datos adicionales del coacreditado",true);
        } catch (Exception e) {
            return new ResponseDTO(null, "Ocurrió un error al registrar la información de datos adicional de coacreditado.", false);
        }
    }

    @PostMapping("/cocreditedadditional")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO FormCocreditedAdditional(@RequestBody CocreditedAdditionalDTO ObjDTO) {
        Customer customerResponse;
        try {
            return cocreditedAdditionalService.save(ObjDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDTO(null, "Ocurrió un error en creación de datos adicional del coacreditado.", false);
        }
    }

    @PutMapping("/cocreditedadditional/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO FormCocreditedAdditional(@PathVariable Integer id,@RequestBody CocreditedAdditionalDTO ObjDTO) {
        try {
            return cocreditedAdditionalService.update(id,ObjDTO);
        } catch (Exception e) {
            return new ResponseDTO(null, "Ocurrió un error en la actualización de los datos adicional del coacreditado.", false);
        }
    }

    @GetMapping("/cocreditedwork/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO FormCocreditedWork(@PathVariable Integer id) {
        try {
            return new ResponseDTO( modelMapper.map(cocreditedWorkService.findById(id), CocreditedWorkDTO.class),"Información laborales del coacreditado",true);
        } catch (Exception e) {
            return new ResponseDTO(null, "Ocurrió un error al registrar la información laborales del coacreditado.", false);
        }
    }

    @PostMapping("/cocreditedwork")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO FormCocreditedWork(@RequestBody CocreditedWorkDTO ObjDTO) {
        Customer customerResponse;
        try {
            return cocreditedWorkService.save(ObjDTO);
        } catch (Exception e) {
            return new ResponseDTO(null, "Ocurrió un error en creación de laborales del coacreditado.", false);
        }
    }

    @PutMapping("/cocreditedwork/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO FormCocreditedWork(@PathVariable Integer id,@RequestBody CocreditedWorkDTO ObjDTO) {
        try {
            return cocreditedWorkService.update(id,ObjDTO);
        } catch (Exception e) {
            return new ResponseDTO(null, "Ocurrió un error en la actualización de los datos laborales del coacreditado.", false);
        }
    }

    @GetMapping("/userboard")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO FormUserBoard() {
        Object data;
        String message;
        Boolean result;
        Type listType;
        try {
            listType = new TypeToken<List<UserBoardDTO>>() {}.getType();
            return new ResponseDTO( modelMapper.map(userService.findAllBoard(), listType),"Usuario",true);
        } catch (Exception ex) {
            data = null;
            result = false;
            message = "Ocurrió un error al crear el usuario.";
        }
        return new ResponseDTO(data, message, result);
    }

    @GetMapping("/userboard/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO FormUserBoard(@PathVariable Integer id) {
        Object data;
        String message;
        Boolean result;
        try {
            return new ResponseDTO( modelMapper.map(userService.findById(id), UserBoardDTO.class),"Usuario",true);
        } catch (Exception ex) {
            data = null;
            result = false;
            message = "Ocurrió un error al actualización el usuario.";
        }
        return new ResponseDTO(data, message, result);
    }


    @PostMapping("/userboard")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO FormUserBoard(@RequestBody UserBoardDTO user) {
        Object data;
        String message;
        Boolean result;

        try {
            Usuario validaUsername = userService.findByUsername(user.getUsername());
            Usuario validaEmail = userService.findByemail(user.getEmail());

            data = null;
            result = false;
            if (validaUsername != null) {
                message = "El nombre de usuario " + user.getUsername() + ". Se encuentra en uso";
            } else if (validaEmail != null) {
                message = "El email " + user.getEmail() + ". Ya se encuentra registrado";
            } else {
                Usuario newUser = new Usuario();

                newUser.setStatus_flag(1);
                newUser.setUsername(user.getEmail());
                newUser.setEmail(user.getEmail());
                //newUser.setPassword(encoder.encode(user.getPassword()));
                newUser.setPassword(user.getPassword());
                newUser.setDtLastLogin(null);
                newUser.setName(user.getName());
                newUser.setPaternalLastName(user.getPaternalLastName());
                newUser.setMotherLastName(user.getMotherLastName());
                newUser.setProfileId(user.getProfileId());
                newUser.setTypeUser(2);
                data = userService.save(newUser);
                result = true;
                message = "Usuario creado";
            }
        } catch (Exception ex) {
            data = null;
            result = false;
            message = "Ocurrió un error al crear el usuario.";
        }
        return new ResponseDTO(data, message, result);
    }

    @PutMapping("/userboard/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO FormUserBoard(@PathVariable Integer id,@RequestBody UserBoardDTO ObjDTO) {
        try {
            return userService.update(id,ObjDTO);
        } catch (Exception e) {
            return new ResponseDTO(null, "Ocurrió un error en la actualización de los datos usuario.", false);
        }
    }

}
