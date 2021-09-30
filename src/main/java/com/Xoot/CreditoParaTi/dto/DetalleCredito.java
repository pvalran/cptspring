package com.Xoot.CreditoParaTi.dto;

import com.Xoot.CreditoParaTi.mapper.DocStatusMap;

import java.util.List;

public class DetalleCredito {
    private CustomerDTO customer;
    private List<DocumentDTO> documents;
    private AdditionalInformationDTO additionalies;
    private List<EconomicDependientiesDto> dependents;
    private SpouseDTO spouse;
    private WorkDTO work;
    private List<ReferenceDTO> references;
    private PropertyDTO property;
    private MedicalQuestionnaireAnswerDTO medicalquestionnaire;
    private CocreditedCustomersDTO cocreditedCustomers;
    private CocreditedAdditionalDTO cocreditedAdditional;
    private CocreditedWorkDTO cocreditedWork;
    private PdfDTO pdf;
    private Boolean solicitud;
    private List<DocStatusMap> documentStatus;

    public List<DocumentDTO> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DocumentDTO> documents) {
        this.documents = documents;
    }

    public AdditionalInformationDTO getAdditionalies() {
        return additionalies;
    }

    public void setAdditionalies(AdditionalInformationDTO additionalies) {
        this.additionalies = additionalies;
    }

    public List<EconomicDependientiesDto> getDependents() {
        return dependents;
    }

    public void setDependents(List<EconomicDependientiesDto> dependents) {
        this.dependents = dependents;
    }

    public SpouseDTO getSpouse() {
        return spouse;
    }

    public void setSpouse(SpouseDTO spouse) {
        this.spouse = spouse;
    }

    public WorkDTO getWork() {
        return work;
    }

    public void setWork(WorkDTO work) {
        this.work = work;
    }

    public List<ReferenceDTO> getReferences() {
        return references;
    }

    public void setReferences(List<ReferenceDTO> references) {
        this.references = references;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public PropertyDTO getProperty() {
        return property;
    }

    public void setProperty(PropertyDTO property) {
        this.property = property;
    }

    public MedicalQuestionnaireAnswerDTO getMedicalquestionnaire() {
        return medicalquestionnaire;
    }

    public void setMedicalquestionnaire(MedicalQuestionnaireAnswerDTO medicalquestionnaire) {
        this.medicalquestionnaire = medicalquestionnaire;
    }

    public CocreditedCustomersDTO getCocreditedCustomers() {
        return cocreditedCustomers;
    }

    public void setCocreditedCustomers(CocreditedCustomersDTO cocreditedCustomers) {
        this.cocreditedCustomers = cocreditedCustomers;
    }

    public CocreditedAdditionalDTO getCocreditedAdditional() {
        return cocreditedAdditional;
    }

    public void setCocreditedAdditional(CocreditedAdditionalDTO cocreditedAdditional) {
        this.cocreditedAdditional = cocreditedAdditional;
    }

    public CocreditedWorkDTO getCocreditedWork() {
        return cocreditedWork;
    }

    public void setCocreditedWork(CocreditedWorkDTO cocreditedWork) {
        this.cocreditedWork = cocreditedWork;
    }

    public PdfDTO getPdf() {
        return pdf;
    }

    public void setPdf(PdfDTO pdf) {
        this.pdf = pdf;
    }


    public Boolean getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Boolean solicitud) {
        this.solicitud = solicitud;
    }

    public List<DocStatusMap> getDocumentStatus() {
        return documentStatus;
    }

    public void setDocumentStatus(List<DocStatusMap> documentStatus) {
        this.documentStatus = documentStatus;
    }
}
